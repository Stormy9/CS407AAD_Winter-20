package com.example.android.joker.presentation

import com.example.android.joker.ui.login.LoginView

interface LoginPresenter : BasePresenter<LoginView> {

  fun onLoginTapped()

  fun onEmailChanged(email: String)

  fun onPasswordChanged(password: String)
}