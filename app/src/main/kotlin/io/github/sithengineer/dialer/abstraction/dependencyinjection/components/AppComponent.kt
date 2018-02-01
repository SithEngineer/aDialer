package io.github.sithengineer.dialer.abstraction.dependencyinjection.components

import dagger.Component
import dagger.android.AndroidInjector
import io.github.sithengineer.dialer.DialerApplication
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.ApplicationScope

@ApplicationScope
@Component(modules = [(AppModule::class)])
interface AppComponent : AndroidInjector<DialerApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<DialerApplication>()
}