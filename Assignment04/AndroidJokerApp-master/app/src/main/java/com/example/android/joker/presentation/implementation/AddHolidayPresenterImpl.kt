package com.example.android.joker.presentation.implementation

import com.example.android.joker.common.isValidHoliday
import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.firebase.database.FirebaseDatabaseInterface
import com.example.android.joker.model.Holiday
import com.example.android.joker.presentation.AddHolidayPresenter
import com.example.android.joker.ui.addHoliday.AddHolidayView
import javax.inject.Inject

class AddHolidayPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AddHolidayPresenter {

  private lateinit var view: AddHolidayView

  private var holidayText = ""

  override fun setView(view: AddHolidayView) {
    this.view = view
  }

  override fun addHolidayTapped() {
    if (isValidHoliday(holidayText)) {
      val holiday = Holiday("", authenticationInterface.getUserName(), authenticationInterface.getUserId(), holidayText)

      databaseInterface.addNewHoliday(holiday) { onAddHolidayResult(it) }
    }
  }

  override fun onHolidayTextChanged(holidayText: String) {
    this.holidayText = holidayText

    if (!isValidHoliday(holidayText)) {
      view.showHolidayError()
    } else {
      view.removeHolidayError()
    }
  }

  private fun onAddHolidayResult(isSuccessful: Boolean) {
    if (isSuccessful) {
      view.onHolidayAdded()
    } else {
      view.showAddHolidayError()
    }
  }
}