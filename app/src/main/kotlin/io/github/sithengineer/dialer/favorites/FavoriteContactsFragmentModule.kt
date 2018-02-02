package io.github.sithengineer.dialer.favorites

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.BaseFragmentModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.usecase.UseCaseModule
import io.github.sithengineer.dialer.usecase.filter.FavoriteUserFilter
import io.github.sithengineer.dialer.usecase.filter.UserFilter
import javax.inject.Named

@Module(includes = [(BaseFragmentModule::class), (UseCaseModule::class)])
abstract class FavoriteContactsFragmentModule {

  @Module
  companion object {

    @JvmStatic
    @Provides
    fun provideUserFilter(): UserFilter = FavoriteUserFilter()

  }

  @Binds
  @Named(BaseFragmentModule.FRAGMENT)
  @FragmentScope
  abstract fun fragment(fragment: FavoriteContactsFragment): Fragment

  @Binds
  @FragmentScope
  abstract fun favoriteContactsView(view: FavoriteContactsFragment): FavoriteContactsView

  @Binds
  @FragmentScope
  abstract fun favoriteContactsPresenter(
      presenter: FavoriteContactsPresenterImpl): FavoriteContactsPresenter

}