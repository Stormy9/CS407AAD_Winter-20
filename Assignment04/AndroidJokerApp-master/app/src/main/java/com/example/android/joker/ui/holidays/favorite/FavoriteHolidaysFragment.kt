package com.example.android.joker.ui.holidays.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.joker.R
// why is this next one, not the full path??
import com.example.android.joker.favoriteHolidaysPresenter
import com.example.android.joker.model.Holiday
import com.example.android.joker.ui.holidays.favorite.list.FavoriteHolidayAdapter
import kotlinx.android.synthetic.main.fragment_holidays.*

class FavoriteHolidaysFragment : Fragment(), FavoriteView {

  private val presenter by lazy { favoriteHolidaysPresenter() }
  private val adapter by lazy { FavoriteHolidayAdapter(presenter::onFavoriteButtonTapped) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_holidays, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.setView(this)
    initUi()

    presenter.getFavoriteHolidays()
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

  override fun clearItems() = adapter.clear()

  override fun showFavoriteHolidays(holidays: List<Holiday>) = adapter.setData(holidays)
}