package com.example.android.joker.ui.addHoliday

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.joker.R
import com.example.android.joker.addHolidayPresenter
import com.example.android.joker.common.onClick
import com.example.android.joker.common.onTextChanged
import com.example.android.joker.common.showGeneralError
import com.example.android.joker.presentation.AddHolidayPresenter
import kotlinx.android.synthetic.main.activity_add_holiday.*

class AddHolidayActivity : AppCompatActivity(), AddHolidayView {

  private val presenter by lazy { addHolidayPresenter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_holiday)
    presenter.setView(this)

    initUi()
  }

  private fun initUi() {
    holidayDescription.onTextChanged { presenter.onHolidayTextChanged(it) }
    addHoliday.onClick { presenter.addHolidayTapped() }
  }

  override fun showHolidayError() {
    holidayDescription.error = getString(R.string.holiday_error)
  }

  override fun removeHolidayError() {
    holidayDescription.error = null
  }

  override fun onHolidayAdded() = finish()

  override fun showAddHolidayError() = showGeneralError(this)
}