package com.example.android.joker.ui.holidays.favorite.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.joker.R
import com.example.android.joker.model.Holiday
import com.example.android.joker.ui.holidays.all.list.HolidayHolder

class FavoriteHolidayAdapter(
    private val onFavoriteClickHandler: (Holiday) -> Unit
) : RecyclerView.Adapter<HolidayHolder>() {

  private val items = mutableListOf<Holiday>()

  override fun getItemCount() = items.size

  fun setData(data: List<Holiday>) {
    items.clear()
    items.addAll(data)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_holiday, parent, false)

    return HolidayHolder(view, onFavoriteClickHandler)
  }

  override fun onBindViewHolder(holder: HolidayHolder, position: Int) = holder.displayData(items[position])

  fun clear() {
    items.clear()
    notifyDataSetChanged()
  }
}