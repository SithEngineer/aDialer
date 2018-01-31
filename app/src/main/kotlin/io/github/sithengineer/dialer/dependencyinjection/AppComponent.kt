package io.github.sithengineer.dialer.dependencyinjection

import dagger.Component
import dagger.android.AndroidInjector
import io.github.sithengineer.dialer.DialerApplication
import io.github.sithengineer.dialer.scope.ApplicationScope

@ApplicationScope
@Component(modules = [(AppModule::class)])
interface AppComponent : AndroidInjector<DialerApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<DialerApplication>()
}