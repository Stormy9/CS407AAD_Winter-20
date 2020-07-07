package com.example.android.joker.presentation

interface BasePresenter<in T> {

  fun setView(view: T)
}