package com.example.android.joker.presentation.implementation

import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.firebase.database.FirebaseDatabaseInterface
import com.example.android.joker.presentation.ProfilePresenter
import com.example.android.joker.ui.profile.ProfileView
import javax.inject.Inject

class ProfilePresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : ProfilePresenter {

  private lateinit var view: ProfileView

  override fun setView(view: ProfileView) {
    this.view = view
  }

  override fun getProfile() {
    databaseInterface.getProfile(authenticationInterface.getUserId()) {
      val userId = authenticationInterface.getUserId()

      view.showUsername(it.username)
      view.showEmail(it.email)
      view.showNumberOfHolidays(it.favoriteHolidays.count { it.authorId==userId })
    }
  }

  override fun logOut() = authenticationInterface.logOut { view.openWelcome() }
}