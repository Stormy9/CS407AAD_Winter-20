package com.example.android.joker.presentation

import com.example.android.joker.ui.addHoliday.AddHolidayView

interface AddHolidayPresenter : BasePresenter<AddHolidayView> {

  fun addHolidayTapped()

  fun onHolidayTextChanged(holidayText: String)
}