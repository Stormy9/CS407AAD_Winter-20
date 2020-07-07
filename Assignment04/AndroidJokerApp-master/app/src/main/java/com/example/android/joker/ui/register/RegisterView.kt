package com.example.android.joker.ui.register

interface RegisterView {

  fun onRegisterSuccess()

  fun showSignUpError()

  fun showUsernameError()

  fun showEmailError()

  fun showPasswordError()

  fun showPasswordMatchingError()
}