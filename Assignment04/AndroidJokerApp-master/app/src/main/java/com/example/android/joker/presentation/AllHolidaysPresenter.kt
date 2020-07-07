package com.example.android.joker.presentation

import com.example.android.joker.model.Holiday
import com.example.android.joker.ui.holidays.all.AllHolidaysView

interface AllHolidaysPresenter : BasePresenter<AllHolidaysView> {

  fun viewReady()

  fun getAllHolidays()

  fun onFavoriteButtonTapped(holiday: Holiday)
}