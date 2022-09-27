package com.example.myapplication.ui.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.constants.Constants
import com.example.myapplication.ui.contactsList.App
import com.example.myapplication.databinding.ActivityDialogBinding
import com.example.myapplication.extensions.addImage
import com.example.myapplication.ui.model.UsersService

class ActivityDialog : DialogFragment() {

    private lateinit var chooseImage: String
    private var _binding: ActivityDialogBinding? = null
    private val binding get() = _binding!!

    private val usersService: UsersService
        get() = (context as App).usersService

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = ActivityDialogBinding.inflate(LayoutInflater.from(context))
        setListeners()
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners(){
        binding.btnArrowBack.setOnClickListener {
            dialog?.dismiss()
        }
        binding.btnSave.setOnClickListener {
            Log.d("!@#", "saving")
            dialog?.let { dialogNonNullable -> saveButtonListener(dialogNonNullable) }
        }
        binding.ibAddPhoto.setOnClickListener {
            generateRandomImage(binding.ivaAvatarInDialog)
        }
    }
    private fun saveButtonListener(dialog: Dialog) {
        if (binding.etPersonName.text.toString() != "" &&
            binding.etOccupy.text.toString() != ""
            && binding.etAddress.text.toString() != ""
            &&

            ::chooseImage.isInitialized
        ) {
            saveButtonAction(dialog)
        } else {

            Toast.makeText(
                dialog.context,
                "Please input all the data and choose avatar",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun saveButtonAction(dialog: Dialog) {
        usersService.addUser(
            binding.etPersonName.text.toString(),
            binding.etOccupy.text.toString(),
            chooseImage, binding.etAddress.text.toString()
        )
        dialog.dismiss()
    }
    private fun generateRandomImage(imageView: ImageView) {
        chooseImage = Constants.imagesArray.random()
        imageView.addImage(chooseImage)
    }
}