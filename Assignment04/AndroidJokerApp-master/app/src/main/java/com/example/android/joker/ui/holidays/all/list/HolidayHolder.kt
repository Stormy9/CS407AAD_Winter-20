package com.example.android.joker.ui.holidays.all.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.android.joker.R
import com.example.android.joker.common.onClick
import com.example.android.joker.model.Holiday
import kotlinx.android.synthetic.main.item_holiday.view.*

class HolidayHolder(
    itemView: View,
    private inline val onFavoriteClickHandler: (Holiday) -> Unit
) : RecyclerView.ViewHolder(itemView) {

  fun displayData(holiday: Holiday) = with(itemView) {
    favoriteButton.onClick { onFavoriteClickHandler(holiday) }

    holidayAuthor.text = holiday.authorName
    holidayDescription.text = holiday.text

    favoriteButton.setImageResource(if(holiday.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border)
  }
}