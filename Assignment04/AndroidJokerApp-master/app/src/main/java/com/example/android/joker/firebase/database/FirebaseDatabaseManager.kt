package com.example.android.joker.firebase.database

import com.google.firebase.database.*
import com.example.android.joker.model.*
import javax.inject.Inject

private const val KEY_USER = "user"
private const val KEY_HOLIDAY = "holiday"
private const val KEY_FAVORITE = "favorite"

class FirebaseDatabaseManager @Inject constructor(private val database: FirebaseDatabase) : FirebaseDatabaseInterface {

  override fun createUser(id: String, name: String, email: String) {
    val user = User(id, name, email)

    database.reference.child(KEY_USER).child(id).setValue(user)
  }

  override fun listenToHolidays(onHolidayAdded: (Holiday) -> Unit) {
    database.reference.child(KEY_HOLIDAY)
        .orderByKey()
        .addChildEventListener(object : ChildEventListener {
          override fun onCancelled(p0: DatabaseError?) = Unit
          override fun onChildMoved(p0: DataSnapshot?, p1: String?) = Unit
          override fun onChildChanged(p0: DataSnapshot?, p1: String?) = Unit
          override fun onChildRemoved(p0: DataSnapshot?) = Unit

          override fun onChildAdded(snapshot: DataSnapshot?, p1: String?) {
            snapshot?.getValue(HolidayResponse::class.java)?.run {
              if (isValid()) {
                onHolidayAdded(mapToHoliday())
              }
            }
          }
        })
  }

  override fun addNewHoliday(holiday: Holiday, onResult: (Boolean) -> Unit) {
    val newHolidayReference = database.reference.child(KEY_HOLIDAY).push()
    val newHoliday = holiday.copy(id = newHolidayReference.key)

    newHolidayReference.setValue(newHoliday).addOnCompleteListener { onResult(it.isSuccessful && it.isComplete) }
  }

  override fun getFavoriteHolidays(userId: String, onResult: (List<Holiday>) -> Unit) {
    database.reference
        .child(KEY_USER)
        .child(userId)
        .child(KEY_FAVORITE)
        .addValueEventListener(object : ValueEventListener {
          override fun onCancelled(error: DatabaseError?) = onResult(listOf())

          override fun onDataChange(snapshot: DataSnapshot?) {
            snapshot?.run {
              val holidays = children.mapNotNull { it.getValue(HolidayResponse::class.java) }

              onResult(holidays.map(HolidayResponse::mapToHoliday))
            }
          }
        })
  }

  override fun changeHolidayFavoriteStatus(holiday: Holiday, userId: String) {
    val reference = database.reference
        .child(KEY_USER)
        .child(userId)
        .child(KEY_FAVORITE)
        .child(holiday.id)

    reference.addListenerForSingleValueEvent(object : ValueEventListener {
      override fun onCancelled(error: DatabaseError?) {}

      override fun onDataChange(snapshot: DataSnapshot?) {
        val oldHoliday = snapshot?.getValue(HolidayResponse::class.java)

        if (oldHoliday!=null) {
          reference.setValue(null)
        } else {
          reference.setValue(holiday)
        }
      }
    })
  }

  override fun getProfile(id: String, onResult: (User) -> Unit) {
    database.reference
        .child(KEY_USER)
        .child(id)
        .addValueEventListener(object : ValueEventListener {
          override fun onCancelled(error: DatabaseError?) = Unit

          override fun onDataChange(snapshot: DataSnapshot?) {
            val user = snapshot?.getValue(UserResponse::class.java)
            val favoriteHolidays = snapshot?.child(KEY_FAVORITE)?.children
                ?.map { it?.getValue(HolidayResponse::class.java) }
                ?.mapNotNull { it?.mapToHoliday() }
                ?: listOf()


            user?.run { onResult(User(id, username, email, favoriteHolidays)) }
          }
        })
  }
}