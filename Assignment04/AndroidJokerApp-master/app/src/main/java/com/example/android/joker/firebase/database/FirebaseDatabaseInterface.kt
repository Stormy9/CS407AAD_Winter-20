package com.example.android.joker.firebase.database

import com.example.android.joker.model.Holiday
import com.example.android.joker.model.User

interface FirebaseDatabaseInterface {

  fun listenToHolidays(onHolidayAdded: (Holiday) -> Unit)

  fun addNewHoliday(holiday: Holiday, onResult: (Boolean) -> Unit)

  fun getFavoriteHolidays(userId: String, onResult: (List<Holiday>) -> Unit)

  fun changeHolidayFavoriteStatus(holiday: Holiday, userId: String)

  fun createUser(id: String, name: String, email: String)

  fun getProfile(id: String, onResult: (User) -> Unit)
}