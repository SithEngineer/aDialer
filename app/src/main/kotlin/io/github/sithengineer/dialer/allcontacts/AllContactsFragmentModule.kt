package io.github.sithengineer.dialer.allcontacts

import android.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.BaseFragmentModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.usecase.UseCaseModule
import io.github.sithengineer.dialer.usecase.filter.EmptyUserFilter
import io.github.sithengineer.dialer.usecase.filter.UserFilter
import javax.inject.Named

@Module(includes = [(BaseFragmentModule::class), (UseCaseModule::class)])
abstract class AllContactsFragmentModule {

  @Module
  companion object {

    @JvmStatic
    @Provides
    fun provideUserFilter(): UserFilter = EmptyUserFilter()

  }

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