package com.example.android.joker.presentation.implementation

import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.firebase.database.FirebaseDatabaseInterface
import com.example.android.joker.model.Holiday
import com.example.android.joker.presentation.FavoriteHolidaysPresenter
import com.example.android.joker.ui.holidays.favorite.FavoriteView
import javax.inject.Inject

class FavoriteHolidaysPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : FavoriteHolidaysPresenter {

  private lateinit var view: FavoriteView

  override fun setView(view: FavoriteView) {
    this.view = view
  }

  override fun getFavoriteHolidays() {
    val id = authenticationInterface.getUserId()

    databaseInterface.getFavoriteHolidays(id) {
      it.forEach { it.isFavorite = true }

      displayItems(it)
    }
  }

  private fun displayItems(items: List<Holiday>) {
    if (items.isEmpty()) {
      view.clearItems()
      view.showNoDataDescription()
    } else {
      view.hideNoDataDescription()
      view.showFavoriteHolidays(items)
    }
  }

  override fun onFavoriteButtonTapped(holiday: Holiday) {
    databaseInterface.changeHolidayFavoriteStatus(holiday, authenticationInterface.getUserId())
  }
}