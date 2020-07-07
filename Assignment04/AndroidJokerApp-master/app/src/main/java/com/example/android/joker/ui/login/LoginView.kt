package com.example.android.joker.ui.login

interface LoginView {

  fun showPasswordError()

  fun showEmailError()

  fun onLoginSuccess()

  fun showLoginError()
}