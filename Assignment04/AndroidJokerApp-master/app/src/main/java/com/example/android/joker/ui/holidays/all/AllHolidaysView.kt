package com.example.android.joker.ui.holidays.all

import com.example.android.joker.model.Holiday

interface AllHolidaysView {

  fun showNoDataDescription()

  fun hideNoDataDescription()

  fun setFavoriteHolidaysIds(favoriteHolidaysIds: List<String>)

  fun addHoliday(holiday: Holiday)
}