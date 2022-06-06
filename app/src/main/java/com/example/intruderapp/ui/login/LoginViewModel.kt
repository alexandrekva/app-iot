package com.example.intruderapp.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel()  {

    fun validateLogin(username: String, password: String): Boolean {
        return username == "admin" && password == "1234"
    }

}