package com.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.constants.Constants.TAG
import com.example.myapplication.contactsList.App
import com.example.myapplication.contactsList.UserActionListener
import com.example.myapplication.contactsList.UsersAdapter
import com.example.myapplication.databinding.MyContactsLayoutBinding
import com.example.myapplication.model.User
import com.example.myapplication.model.UsersService
import com.example.myapplication.model.usersListener

class MyContactsActivity : AppCompatActivity() {

    private lateinit var adapter: UsersAdapter
    private lateinit var binding: MyContactsLayoutBinding



    private val usersService: UsersService
        get() = (applicationContext as App).usersService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyContactsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapterAndLayoutManager()
        setListeners()
    }

    private fun setListeners() {
        usersService.addListener(usersListener)
        binding.btnAddContact.setOnClickListener {
            ActivityDialog().show(
                supportFragmentManager, TAG)
        }
    }



    private fun setAdapterAndLayoutManager() {
        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }
        })
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        addItemDecoration()
    }

    private fun addItemDecoration() {
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.decoration_recyclerview_drawable,
                null
            )!!
        )
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }




    override fun onDestroy() {
        super.onDestroy()
        usersService.deleteListener(usersListener)
    }

    private val usersListener: usersListener = {
        adapter.users = it
    }


}