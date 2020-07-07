package com.example.android.joker.presentation

import com.example.android.joker.ui.profile.ProfileView

interface ProfilePresenter : BasePresenter<ProfileView> {

  fun getProfile()

  fun logOut()
}