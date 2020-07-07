package com.example.android.joker.ui.holidays.all

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.joker.R
import com.example.android.joker.allHolidaysPresenter
import com.example.android.joker.model.Holiday
import com.example.android.joker.ui.holidays.all.list.HolidayAdapter
import kotlinx.android.synthetic.main.fragment_holidays.*

class AllHolidaysFragment : Fragment(), AllHolidaysView {

  private val presenter by lazy { allHolidaysPresenter() }
  private val adapter by lazy { HolidayAdapter(presenter::onFavoriteButtonTapped) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_holidays, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
    presenter.setView(this)

    presenter.viewReady()
  }

  override fun addHoliday(holiday: Holiday) {
    adapter.addHoliday(holiday)
    noItems.visibility = if (adapter.itemCount!=0) View.INVISIBLE else View.VISIBLE
  }

  private fun initUi() {
    holidays.layoutManager = LinearLayoutManager(activity)
    holidays.setHasFixedSize(true)
    holidays.adapter = adapter
  }

  override fun showNoDataDescription() {
    noItems.visibility = View.VISIBLE
  }

  override fun hideNoDataDescription() {
    noItems.visibility = View.GONE
  }

  override fun setFavoriteHolidaysIds(favoriteHolidaysIds: List<String>) = adapter.setFavoriteHolidaysIds(favoriteHolidaysIds)
}