package io.github.sithengineer.dialer.abstraction.dependencyinjection.components

import dagger.Module
import dagger.Provides
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class BaseFragmentModule {

  @Module
  companion object {
    const val FRAGMENT = "BaseFragmentModule.fragment"

    // provide a child fragment manager here if needed.
  }
}