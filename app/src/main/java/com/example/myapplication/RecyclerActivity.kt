package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityRecyclerBinding
import com.example.myapplication.model.User
import com.example.myapplication.model.UsersService
import com.example.myapplication.model.usersListener

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding1: ActivityRecyclerBinding
    private lateinit var adapter: UsersAdapter

    private val usersService: UsersService
        get() = (applicationContext as App).UsersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        adapter = UsersAdapter(object : UserActionListener {

            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)

            }

        })

        val layoutManager = LinearLayoutManager(this)
        binding1.recyclerView.layoutManager = layoutManager
        binding1.recyclerView.adapter = adapter
        usersService.addListener(usersListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.deleteListener(usersListener)
    }

    private val usersListener: usersListener = {
        adapter.users = it
    }
}