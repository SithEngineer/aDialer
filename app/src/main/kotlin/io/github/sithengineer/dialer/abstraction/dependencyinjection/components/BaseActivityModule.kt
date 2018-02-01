package io.github.sithengineer.dialer.abstraction.dependencyinjection.components

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.ActivityScope
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class BaseActivityModule {

  @Binds
  @ActivityScope
  abstract fun activityContext(activity: Activity): Context

}