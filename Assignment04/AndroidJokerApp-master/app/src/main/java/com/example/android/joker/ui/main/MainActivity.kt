package com.example.android.joker.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.joker.R
import com.example.android.joker.common.onClick
import com.example.android.joker.common.onPageChange
import com.example.android.joker.ui.addHoliday.AddHolidayActivity
import com.example.android.joker.ui.holidays.all.AllHolidaysFragment
import com.example.android.joker.ui.holidays.favorite.FavoriteHolidaysFragment
import com.example.android.joker.ui.main.pager.MainPagerAdapter
import com.example.android.joker.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  companion object {
    fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initUi()
  }

  private fun initUi() {
    val adapter = MainPagerAdapter(supportFragmentManager)
    adapter.setPages(listOf(AllHolidaysFragment(), FavoriteHolidaysFragment(), ProfileFragment()))

    mainPager.adapter = adapter
    mainPager.offscreenPageLimit = 3
    bottomNavigation.setOnNavigationItemSelectedListener {
      switchNavigationTab(it.order)
      true
    }

    mainPager.onPageChange { position ->
      val item = bottomNavigation.menu.getItem(position)

      bottomNavigation.selectedItemId = item.itemId
    }

    addHoliday.onClick { startActivity(Intent(this, AddHolidayActivity::class.java)) }
  }

  private fun switchNavigationTab(position: Int) = mainPager.setCurrentItem(position, true)
}