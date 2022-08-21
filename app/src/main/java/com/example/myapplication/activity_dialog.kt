package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class activity_dialog :DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener =DialogInterface.OnClickListener{_,which->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to which))
        }
        val view=layoutInflater.inflate(R.layout.activity_dialog,null)
        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle("Title")
            .create()

    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    companion object {
        @JvmStatic val TAG= activity_dialog::class.java.simpleName
        @JvmStatic val REQUEST_KEY ="$TAG:defaultRequestKey"
        @JvmStatic val KEY_RESPONSE= "RESPONSE"
    }
}