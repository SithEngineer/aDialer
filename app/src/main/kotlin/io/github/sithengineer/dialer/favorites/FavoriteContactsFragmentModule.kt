package io.github.sithengineer.dialer.favorites

import android.app.Fragment
import dagger.Binds
import dagger.Module
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.BaseFragmentModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import javax.inject.Named

@Module(includes = [(BaseFragmentModule::class)])
abstract class FavoriteContactsFragmentModule {

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