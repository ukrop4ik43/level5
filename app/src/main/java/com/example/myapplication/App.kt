package com.example.myapplication

import android.app.Application
import com.example.myapplication.model.UsersService

class App : Application() {
    var UsersService = UsersService()

}