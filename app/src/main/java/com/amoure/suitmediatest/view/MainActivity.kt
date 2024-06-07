package com.amoure.suitmediatest.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.amoure.suitmediatest.R
import com.amoure.suitmediatest.databinding.ActivityMainBinding
import com.amoure.suitmediatest.view.second.SecondActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playAnimation()

        binding.btCheck.setOnClickListener {
            val palindrome = binding.edPalindrome.text.toString()
            val message = if (isPalindrome(palindrome)) {
                String.format(resources.getString(R.string.yes_palindrome), palindrome)
            } else {
                String.format(resources.getString(R.string.no_palindrome), palindrome)
            }
            showDialog(resources.getString(R.string.is_palindrome), message)
        }
        binding.btNext.setOnClickListener {
            if (binding.edName.text.toString().isEmpty()) {
                showToast(getString(R.string.empty_name))
            } else {
                val name = binding.edName.text.toString()
                val moveIntent = Intent(this@MainActivity, SecondActivity::class.java)
                moveIntent.putExtra(SecondActivity.NAME, name)
                startActivity(moveIntent)
            }
        }
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(resources.getString(R.string.next)) { _, _ ->
            }
            create()
            show()
        }
    }

    private fun isPalindrome(input: String): Boolean {
        val filteredString = input.filter { it.isLetterOrDigit() }.lowercase()
        return filteredString == filteredString.reversed()
    }

    private fun animate(v: View): ObjectAnimator {
        return ObjectAnimator.ofFloat(v, View.ALPHA, 1f).setDuration(100)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun playAnimation() {
        with(binding) {
            val tvName = animate(tvName)
            val edlName = animate(edlName)
            val tvPalindrome = animate(tvPalindrome)
            val edlPalindrome = animate(edlPalindrome)
            val btCheck = animate(btCheck)
            val btNext = animate(btNext)

            AnimatorSet().apply {
                playSequentially(
                    tvName,
                    edlName,
                    tvPalindrome,
                    edlPalindrome,
                    btCheck,
                    btNext,
                )
                startDelay = 100
            }.start()
        }
    }
}