package io.github.sithengineer.dialer.dependencyinjection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import io.github.sithengineer.dialer.DialerApplication
import io.github.sithengineer.dialer.home.HomeActivity
import io.github.sithengineer.dialer.home.HomeActivityModule
import io.github.sithengineer.dialer.scope.ActivityScope
import io.github.sithengineer.dialer.scope.ApplicationScope

@Module(includes = [(AndroidInjectionModule::class)])
abstract class AppModule {

  @Module
  companion object {
    private const val SHARED_PREF_NAME = "DialerApp"

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideSharedPreferences(
        app: DialerApplication): SharedPreferences = app.getSharedPreferences(
        SHARED_PREF_NAME, Context.MODE_PRIVATE)
  }

  @Binds
  @ApplicationScope
  abstract fun provideContext(app: DialerApplication): Application

  @ActivityScope
  @ContributesAndroidInjector(modules = [(HomeActivityModule::class)])
  internal abstract fun mainActivityInjector(): HomeActivity
}