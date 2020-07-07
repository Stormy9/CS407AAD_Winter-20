package com.example.android.joker.presentation

import com.example.android.joker.model.Holiday
import com.example.android.joker.ui.holidays.favorite.FavoriteView

interface FavoriteHolidaysPresenter : BasePresenter<FavoriteView> {

  fun getFavoriteHolidays()

  fun onFavoriteButtonTapped(holiday: Holiday)
}