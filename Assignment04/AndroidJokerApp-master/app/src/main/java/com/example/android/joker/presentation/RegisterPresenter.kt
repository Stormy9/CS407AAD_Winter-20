package com.example.android.joker.presentation

import com.example.android.joker.ui.register.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {

  fun onUsernameChanged(username: String)

  fun onEmailChanged(email: String)

  fun onPasswordChanged(password: String)

  fun onRepeatPasswordChanged(repeatPassword: String)

  fun onRegisterTapped()

}