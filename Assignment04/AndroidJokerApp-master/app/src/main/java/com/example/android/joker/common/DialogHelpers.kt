package com.example.android.joker.common

import android.content.Context
import android.support.v7.app.AlertDialog
import com.example.android.joker.R


fun showGeneralError(from: Context) {
  AlertDialog.Builder(from)
      .setTitle(from.getString(R.string.error_title))
      .setMessage(from.getString(R.string.error_message))
      .setPositiveButton(from.getString(R.string.general_positive_button), { dialog, _ -> dialog.dismiss() })
      .show()
}