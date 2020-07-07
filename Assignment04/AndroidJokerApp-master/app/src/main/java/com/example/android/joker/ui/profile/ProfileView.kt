package com.example.android.joker.ui.profile

interface ProfileView {

  fun showUsername(username: String)

  fun showEmail(email: String)

  fun showNumberOfHolidays(holidaysCount: Int)

  fun openWelcome()
}