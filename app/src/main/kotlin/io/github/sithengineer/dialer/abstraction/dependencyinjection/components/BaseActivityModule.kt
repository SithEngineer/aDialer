package io.github.sithengineer.dialer.abstraction.dependencyinjection.components

import android.app.Activity
import android.content.Context
import dagger.Binds
import dagger.Module
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.ActivityScope

@Module
abstract class BaseActivityModule {

  @Binds
  @ActivityScope
  abstract fun activityContext(activity: Activity): Context

}