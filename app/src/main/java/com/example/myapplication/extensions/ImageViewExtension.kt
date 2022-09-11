package com.example.myapplication.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.User

fun ImageView.addImage(user: User) {
    Glide.with(this.context)
        .load(user.photo)
        .circleCrop()
        .placeholder(R.drawable.me)
        .error(R.drawable.me)
        .into(this)
}