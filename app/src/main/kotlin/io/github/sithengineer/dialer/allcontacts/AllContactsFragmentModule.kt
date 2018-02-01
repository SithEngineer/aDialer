package io.github.sithengineer.dialer.allcontacts

import android.app.Fragment
import dagger.Binds
import dagger.Module
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.BaseFragmentModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import javax.inject.Named

@Module(includes = [(BaseFragmentModule::class)])
abstract class AllContactsFragmentModule {

  @Binds
  @Named(BaseFragmentModule.FRAGMENT)
  @FragmentScope
  abstract fun fragment(fragment: AllContactsFragment): Fragment

  @Binds
  @FragmentScope
  abstract fun allContactsView(view: AllContactsFragment): AllContactsView

  @Binds
  @FragmentScope
  abstract fun allContactsPresenter(presenter: AllContactsPresenterImpl): AllContactsPresenter

}