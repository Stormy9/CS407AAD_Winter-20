package com.example.android.joker.presentation.implementation

import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.presentation.WelcomePresenter
import com.example.android.joker.ui.welcome.WelcomeView
import javax.inject.Inject

class WelcomePresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface
) : WelcomePresenter {

  private lateinit var view: WelcomeView

  override fun setView(view: WelcomeView) {
    this.view = view
  }

  override fun viewReady() {
    if (authenticationInterface.getUserId().isNotBlank()) {
      view.startMainScreen()
    }
  }
}