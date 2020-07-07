package com.example.android.joker.model

import com.example.android.joker.common.isEmailValid
import com.example.android.joker.common.isPasswordValid

data class LoginRequest(var email: String = "",
                        var password: String = "") {

  fun isValid() = isEmailValid(email) && isPasswordValid(password)
}