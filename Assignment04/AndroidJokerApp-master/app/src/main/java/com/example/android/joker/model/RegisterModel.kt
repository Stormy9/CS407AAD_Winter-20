package com.example.android.joker.model

import com.example.android.joker.common.arePasswordsSame
import com.example.android.joker.common.isEmailValid
import com.example.android.joker.common.isUsernameValid

data class RegisterRequest(var name: String = "",
                           var email: String = "",
                           var password: String = "",
                           var repeatPassword: String = "") {

  fun isValid(): Boolean = isUsernameValid(name)
      && isEmailValid(email)
      && arePasswordsSame(password, repeatPassword)

}