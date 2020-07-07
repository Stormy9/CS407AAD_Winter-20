package com.example.android.joker.di.module

import com.example.android.joker.presentation.*
import com.example.android.joker.presentation.implementation.*
import dagger.Binds
import dagger.Module

@Module(includes = [InteractionModule::class])
abstract class PresentationModule {

  @Binds
  abstract fun loginPresenter(loginPresenter: LoginPresenterImpl): LoginPresenter

  @Binds
  abstract fun registerPresenter(registerPresenter: RegisterPresenterImpl): RegisterPresenter

  @Binds
  abstract fun allHolidaysPresenter(allHolidaysPresenterImpl: AllHolidaysPresenterImpl): AllHolidaysPresenter

  @Binds
  abstract fun favoriteHolidaysPresenter(favoriteHolidaysPresenterImpl: FavoriteHolidaysPresenterImpl): FavoriteHolidaysPresenter

  @Binds
  abstract fun profilePresenter(profilePresenterImpl: ProfilePresenterImpl): ProfilePresenter

  @Binds
  abstract fun addHolidayPresenter(addHolidayPresenterImpl: AddHolidayPresenterImpl): AddHolidayPresenter

  @Binds
  abstract fun welcomePresenter(welcomePresenterImpl: WelcomePresenterImpl): WelcomePresenter
}