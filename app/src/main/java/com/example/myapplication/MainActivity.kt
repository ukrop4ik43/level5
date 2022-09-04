package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import com.example.myapplication.Constants.DOG_SIGN
 
import com.example.myapplication.databinding.DetailViewBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: DetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        Log.d(TAG, "$message")
        transformName(message)
    }

    private fun transformName(message: String?) {
        var name = addingNames(message)
        name = name.replace(oldChar = '.', newChar = ' ', ignoreCase = false)
        binding.UserNameTextView.text = name.capitalizeWords()
    }


    private fun String.capitalizeWords() = split(" ")
        .joinToString(" ") { it -> it.lowercase().replaceFirstChar { it.uppercase() } }


    private fun addingNames(message: String?): String {

        val index = message?.indexOf(DOG_SIGN) ?: -1
        return message?.substring(
            0, if (index < 0) message.length else index
        ).toString()
    }

    private companion object {
        private const val TAG = "MainActivity"
    }
}