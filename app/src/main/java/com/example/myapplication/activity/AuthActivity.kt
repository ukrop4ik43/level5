package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.myapplication.constants.Constants.LETTERS
import com.example.myapplication.constants.Constants.MINIMUM_EIGHT
import com.example.myapplication.constants.Constants.MINIMUM_ONE_LOWER
import com.example.myapplication.constants.Constants.MINIMUM_ONE_NUMBER
import com.example.myapplication.constants.Constants.NUMBERS
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val view = binding.root
        setContentView(view)
        setListeners()
    }

    private fun setListeners() {
        emailFocusListener()
        passFocusListener()
        setOnClickListener()
        pressedEnter()
    }

    private fun pressedEnter() {
        binding.tePass.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                sendMessage()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun setOnClickListener() {
        binding.btnRegister.setOnClickListener {
            if (binding.etEmail.text.toString() != "") sendMessage()
            else {
                Toast.makeText(
                    this,
                    "Please input login and password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun sendMessage() {
        val emailVal = binding.etEmail.text.toString()
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, emailVal)
        }
        startActivity(intent)
        finish()
    }

    private fun emailFocusListener() {
        binding.run {
            etEmail.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    cntEmail.helperText = validEmail()
                }
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return getString(R.string.invalid_email_address)
        }
        return null
    }


    private fun passFocusListener() {
        binding.tePass.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.cntPass.helperText = validPass()
            }
        }
    }

    private fun validPass(): String? {
        val passText = binding.tePass.text.toString()
        if (passText.length < 8) {
            return MINIMUM_EIGHT
        }
        if (!passText.matches(NUMBERS.toRegex())) {
            return MINIMUM_ONE_NUMBER
        }
        if (!passText.matches(LETTERS.toRegex())) {
            return MINIMUM_ONE_LOWER
        }
        return null
    }
}