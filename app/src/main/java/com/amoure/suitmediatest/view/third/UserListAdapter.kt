package com.amoure.suitmediatest.view.third

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amoure.suitmediatest.data.response.UserItem
import com.amoure.suitmediatest.databinding.ItemUserBinding
import com.bumptech.glide.Glide

class UserListAdapter : PagingDataAdapter<UserItem, UserListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user, onItemClickCallback)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserItem, onItemClickCallback: OnItemClickCallback) {
            with(binding) {
                val fullName = String.format("%s %s", user.firstName, user.lastName)
                tvName.text = fullName
                tvEmail.text = user.email
                Glide.with(cvUser.context)
                    .load(user.avatar)
                    .into(imgAvatar)
                cvUser.setOnClickListener {
                    onItemClickCallback.onItemClicked(fullName)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(fullName: String)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}