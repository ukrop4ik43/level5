package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.constants.Constants
import com.example.myapplication.databinding.FragmentFirstBinding

import com.example.myapplication.ui.activity.MainActivity


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        val activityOne: MainActivity? = activity as MainActivity?
        val textName = activityOne?.sendData()
        transformName(textName)
        Log.d("MainActivity", "email in fragment value- $textName")
        return binding.root
    }


    private fun transformName(message: String?) {
        var name = addingNames(message)
        name = name.replace(oldChar = '.', newChar = ' ', ignoreCase = false)
        binding.tvDetailUserName.text = name.capitalizeWords()
    }


    private fun String.capitalizeWords() = split(" ")
        .joinToString(" ") { it -> it.lowercase().replaceFirstChar { it.uppercase() } }


    private fun addingNames(message: String?): String {
        val index = message?.indexOf(Constants.DOG_SIGN) ?: -1
        return message?.substring(
            0, if (index < 0) message.length else index
        ).toString()
    }


}