package com.example.android.joker.model

data class HolidayResponse(val id: String = "",
                        val authorName: String = "",
                        val authorId: String = "",
                        val text: String = "")

fun HolidayResponse.isValid() = id.isNotBlank()
    && authorName.isNotBlank()
    && authorId.isNotBlank()
    && text.isNotBlank()

fun HolidayResponse.mapToHoliday() = Holiday(id, authorName, authorId, text)

data class Holiday(val id: String,
                val authorName: String,
                val authorId: String,
                val text: String,
                var isFavorite: Boolean = false)