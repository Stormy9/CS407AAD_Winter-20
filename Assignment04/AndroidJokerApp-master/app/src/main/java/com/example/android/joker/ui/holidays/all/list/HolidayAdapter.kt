package com.example.android.joker.ui.holidays.all.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.joker.R
import com.example.android.joker.model.Holiday

class HolidayAdapter(
    private val onFavoriteClickHandler: (Holiday) -> Unit
) : RecyclerView.Adapter<HolidayHolder>() {

  private val items = mutableListOf<Holiday>()
  private val favoriteHolidaysIds = mutableListOf<String>()

  override fun getItemCount() = items.size

  fun setFavoriteHolidaysIds(ids: List<String>) {
    favoriteHolidaysIds.clear()
    favoriteHolidaysIds.addAll(ids)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_holiday, parent, false)

    return HolidayHolder(view, onFavoriteClickHandler)
  }

  override fun onBindViewHolder(holder: HolidayHolder, position: Int) {
    val holiday = items[position].apply { isFavorite = id in favoriteHolidaysIds }

    holder.displayData(holiday)
  }

  fun addHoliday(holiday: Holiday) {
    items.add(holiday)
    notifyItemInserted(items.size - 1)
  }
}