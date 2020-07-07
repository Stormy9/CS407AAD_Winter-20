package com.example.android.joker.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.joker.R
import com.example.android.joker.common.onClick
import com.example.android.joker.ui.login.LoginActivity
import com.example.android.joker.ui.main.MainActivity
import com.example.android.joker.ui.register.RegisterActivity
import com.example.android.joker.welcomePresenter
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity(), WelcomeView {

  private val presenter by lazy { welcomePresenter() }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_welcome)
    presenter.setView(this)

    presenter.viewReady()

    registerButton.onClick { startActivity(Intent(this, RegisterActivity::class.java)) }
    loginButton.onClick { startActivity(Intent(this, LoginActivity::class.java)) }
  }

  override fun startMainScreen() = startActivity(MainActivity.getLaunchIntent(this))
}
