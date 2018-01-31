package io.github.sithengineer.dialer.dependencyinjection

import dagger.Module

@Module
abstract class BaseFragmentModule {

  // @Module
  companion object {
    const val FRAGMENT = "BaseFragmentModule.fragment"

    // provide a child fragment manager here if needed.
  }
}