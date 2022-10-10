package com.example.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.extensions.addImage

class DetailFragment : Fragment() {
    val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.btnArrowBack.setOnClickListener{
            findNavController().navigate(R.id.action_detailFragment_to_viewPagerFragment)
        }
        updateViewOfDetails()
        return binding.root
    }

    private fun updateViewOfDetails() {
        val userToUpdate = args.contacts
        binding.tvDetailUserName.text = userToUpdate.name
        binding.tvDetailCareer.text = userToUpdate.occupy
        binding.tvDetailHomeAddress.text = userToUpdate.homeAddress
        binding.ivDetailAvatar.addImage(userToUpdate)
    }
}