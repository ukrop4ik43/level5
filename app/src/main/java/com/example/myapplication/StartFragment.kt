package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.contactsList.App
import com.example.myapplication.contactsList.UserActionListener
import com.example.myapplication.contactsList.UsersAdapter
import com.example.myapplication.model.User
import com.example.myapplication.model.UsersService
import com.example.myapplication.model.usersListener


class StartFragment : Fragment() {

    private lateinit var adapter: UsersAdapter

    private val usersService: UsersService
        get() = (requireContext() as App).usersService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_start, container, false)
       val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }
        })
        recyclerView.layoutManager=LinearLayoutManager(activity)
        recyclerView.adapter=adapter
        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        usersService.deleteListener(usersListener)
    }

    private val usersListener: usersListener = {
        adapter.users = it
    }

}