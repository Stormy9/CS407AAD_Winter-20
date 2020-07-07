package com.example.android.joker.di.module

import com.example.android.joker.firebase.authentication.FirebaseAuthenticationInterface
import com.example.android.joker.firebase.authentication.FirebaseAuthenticationManager
import com.example.android.joker.firebase.database.FirebaseDatabaseInterface
import com.example.android.joker.firebase.database.FirebaseDatabaseManager
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [FirebaseModule::class])
@Singleton
abstract class InteractionModule {

  @Binds
  abstract fun authentication(authentication: FirebaseAuthenticationManager): FirebaseAuthenticationInterface

  @Binds
  abstract fun database(database: FirebaseDatabaseManager): FirebaseDatabaseInterface
}