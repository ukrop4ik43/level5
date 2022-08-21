package com.example.myapplication

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityRecyclerBinding
import com.example.myapplication.activity_dialog
import com.example.myapplication.databinding.MyContactsLayoutBinding
import com.example.myapplication.model.User
import com.example.myapplication.model.UsersService
import com.example.myapplication.model.usersListener

class MyContactsActivity : AppCompatActivity() {
    private lateinit var adapter: UsersAdapter
    private lateinit var btnCustomAlertDialog: Button
    private lateinit var binding: MyContactsLayoutBinding

    private val usersService: UsersService
        get() = (applicationContext as App).UsersService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MyContactsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = UsersAdapter(object : UserActionListener {

            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }

        })

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        usersService.addListener(usersListener)
        btnCustomAlertDialog=findViewById(R.id.button)
        btnCustomAlertDialog.setOnClickListener{
            val dialog = Dialog (this)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.activity_dialog)
            val btnClose=dialog.findViewById<ImageButton>(R.id.arrowBackDialog)
            val btnSave=dialog.findViewById<Button>(R.id.saveButton)
            btnClose.setOnClickListener{
                dialog.dismiss()
            }
            btnSave.setOnClickListener{

            }
           dialog.show()
        }
    }





    override fun onDestroy() {
        super.onDestroy()
        usersService.deleteListener(usersListener)
    }

    private val usersListener: usersListener = {
        adapter.users = it
    }
}