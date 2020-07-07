package com.example.android.joker.presentation.implementation

import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.firebase.database.FirebaseDatabaseInterface
import com.example.android.joker.model.Holiday
import com.example.android.joker.presentation.AllHolidaysPresenter
import com.example.android.joker.ui.holidays.all.AllHolidaysView
import javax.inject.Inject

class AllHolidaysPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AllHolidaysPresenter {

  private lateinit var view: AllHolidaysView

  override fun setView(view: AllHolidaysView) {
    this.view = view
  }

  override fun viewReady() {
    databaseInterface.getFavoriteHolidays(authenticationInterface.getUserId()) { onFavoriteHolidaysResult(it) }
    getAllHolidays()
  }

  private fun onFavoriteHolidaysResult(favoriteHolidays: List<Holiday>) = view.setFavoriteHolidaysIds(favoriteHolidays.map { it.id })

  override fun getAllHolidays() = databaseInterface.listenToHolidays { view.addHoliday(it) }

  override fun onFavoriteButtonTapped(holiday: Holiday) {
    databaseInterface.changeHolidayFavoriteStatus(holiday, authenticationInterface.getUserId())
  }
}

