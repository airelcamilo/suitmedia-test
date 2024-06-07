package com.amoure.suitmediatest.view.second

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.amoure.suitmediatest.databinding.ActivitySecondBinding
import com.amoure.suitmediatest.view.third.ThirdActivity


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private var name: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra(NAME)
        with(binding) {
            tvName.text = name

            topAppBar.setNavigationOnClickListener {
                finish()
            }
            btChooseUser.setOnClickListener {
                val moveIntent = Intent(this@SecondActivity, ThirdActivity::class.java)
                launcherThirdActivity.launch(moveIntent)
            }
        }
        playAnimation()
    }

    private val launcherThirdActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (Activity.RESULT_OK == result.resultCode) {
            binding.tvSelectedUserName.text = result.data?.getStringExtra(ThirdActivity.CHOOSEN_USER_NAME)
        }
    }

    private fun animate(v: View): ObjectAnimator {
        return ObjectAnimator.ofFloat(v, View.ALPHA, 1f).setDuration(100)
    }

    private fun playAnimation() {
        with(binding) {
            val tvWelcome = animate(tvWelcome)
            val tvName = animate(tvName)
            val tvSelectedUserName = animate(tvSelectedUserName)
            val btChooseUser = animate(btChooseUser)

            AnimatorSet().apply {
                playSequentially(
                    tvWelcome,
                    tvName,
                    tvSelectedUserName,
                    btChooseUser
                )
                startDelay = 100
            }.start()
        }
    }

    companion object {
        const val NAME = "name"
    }
}