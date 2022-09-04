package com.example.myapplication.contactsList

import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.MyContactsLayoutBinding
import com.example.myapplication.model.User
import com.example.myapplication.model.UsersService
import com.example.myapplication.model.usersListener

class MyContactsActivity : AppCompatActivity() {

    private lateinit var chooseImage: String
    private lateinit var adapter: UsersAdapter
    private lateinit var binding: MyContactsLayoutBinding


    private val usersService: UsersService
        get() = (applicationContext as App).UsersService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyContactsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapterAndLayoutManager()
        setListeners()
    }

    private fun setListeners() {
        usersService.addListener(usersListener)
        val btnAlertDialogOpener: Button = findViewById(R.id.addContactB)
        btnAlertDialogOpener.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.activity_dialog)
            val imageView: ImageView = dialog.findViewById(R.id.AvatarImageViewInDialog)
            dialog.findViewById<ImageButton>(R.id.arrowBackButton).setOnClickListener {
                dialog.dismiss()
            }
            dialog.findViewById<Button>(R.id.saveButton).setOnClickListener {
                saveButtonListener(dialog)
            }
            dialog.findViewById<ImageButton>(R.id.addPhotoImageButton).setOnClickListener {
                generateRandomImage(imageView)
            }
            dialog.show()
        }
    }

    private fun saveButtonListener(dialog: Dialog) {
        if (dialog.findViewById<EditText>(R.id.editTextPersonName).text.toString() != "" &&
            dialog.findViewById<EditText>(R.id.editTextCareer).text.toString() != ""
            && dialog.findViewById<EditText>(R.id.editTextTextPostalAddress).text.toString() != ""
            && ::chooseImage.isInitialized
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
            dialog.findViewById<EditText>(R.id.editTextPersonName).text.toString(),
            dialog.findViewById<EditText>(R.id.editTextCareer).text.toString(),
            chooseImage,dialog.findViewById<EditText>(R.id.editTextTextPostalAddress).text.toString()
        )
        adapter.notifyItemInserted(usersService.getSize() - 1)
        dialog.dismiss()
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
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.my_drawable))
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun generateRandomImage(imageView: ImageView) {
        val images = arrayOf(
            "https://maximum.fm/uploads/media_news/2020/05/5ec25857a251b581364253.png?w=1200&h=675&il&q=80&output=jpg",
            "https://mir-s3-cdn-cf.behance.net/project_modules/disp/ea7a3c32163929.567197ac70bda.png",
            "https://img.freepik.com/premium-vector/smiling-girl-avatar_102172-32.jpg",
            "https://img.freepik.com/premium-vector/the-face-of-a-cute-girl-avatar-of-a-young-girl-portrait-vector-flat-illustration_192760-82.jpg?w=2000",
            "https://i0.wp.com/www.cssscript.com/wp-content/uploads/2020/12/Customizable-SVG-Avatar-Generator-In-JavaScript-Avataaars.js.png?fit=438%2C408&ssl=1"
        )
        chooseImage = images.random()
        Glide.with(this).load(chooseImage).into(imageView)
    }


    override fun onDestroy() {
        super.onDestroy()
        usersService.deleteListener(usersListener)
    }

    private val usersListener: usersListener = {
        adapter.users = it
    }


}