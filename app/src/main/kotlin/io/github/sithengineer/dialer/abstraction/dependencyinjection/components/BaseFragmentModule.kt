package io.github.sithengineer.dialer.abstraction.dependencyinjection.components

import dagger.Module

@Module
abstract class BaseFragmentModule {

  @Module
  companion object {
    const val FRAGMENT = "BaseFragmentModule.fragment"

    // provide a child fragment manager here if needed.
  }
}