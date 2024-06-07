package com.amoure.suitmediatest.view.third

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.amoure.suitmediatest.databinding.ActivityThirdBinding
import com.amoure.suitmediatest.view.ViewModelFactory


class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private val thirdViewModel by viewModels<ThirdViewModel> {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
        getUsers()
    }

    private fun getUsers() {
        val adapter = UserListAdapter()
        with(binding) {
            rvUsers.adapter = adapter
            adapter.addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                    rvUsers.isVisible = false
                    tvEmptyUsers.isVisible = true
                } else {
                    rvUsers.isVisible = true
                    tvEmptyUsers.isVisible = false
                }
            }
            adapter.setOnItemClickCallback(object : UserListAdapter.OnItemClickCallback {
                override fun onItemClicked(fullName: String) {
                    val returnIntent = Intent()
                    returnIntent.putExtra(CHOOSEN_USER_NAME, fullName)
                    setResult(RESULT_OK, returnIntent)
                    finish()
                }
            })
            refreshLayout.setOnRefreshListener {
                adapter.refresh()
                refreshLayout.isRefreshing = false
            }
        }
        thirdViewModel.users.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    companion object {
        const val CHOOSEN_USER_NAME = "choosen_user_name"
    }
}