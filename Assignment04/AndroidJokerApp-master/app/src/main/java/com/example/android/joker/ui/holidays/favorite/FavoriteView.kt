package com.example.android.joker.ui.holidays.favorite

import com.example.android.joker.model.Holiday

interface FavoriteView {

  fun showFavoriteHolidays(holidays: List<Holiday>)

  fun showNoDataDescription()

  fun hideNoDataDescription()

  fun clearItems()
}