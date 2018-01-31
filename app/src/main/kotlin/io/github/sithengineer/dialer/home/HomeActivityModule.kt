package io.github.sithengineer.dialer.home

import android.app.Activity
import dagger.Binds
import dagger.Module
import io.github.sithengineer.dialer.dependencyinjection.BaseActivityModule
import io.github.sithengineer.dialer.scope.ActivityScope

@Module(includes = [(BaseActivityModule::class), (HomeFragmentProvider::class)])
abstract class HomeActivityModule {

  companion object {
    private const val SHOW_INTRO = "show_intro"
  }

  @Binds
  @ActivityScope
  abstract fun activity(homeActivity: HomeActivity): Activity

  @Binds
  @ActivityScope
  abstract fun homeView(homeActivity: HomeActivity): HomeView

  @Binds
  @ActivityScope
  abstract fun homePresenter(presenter: HomePresenterImpl): HomePresenter
}