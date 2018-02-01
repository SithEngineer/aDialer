package io.github.sithengineer.dialer.callhistory

import android.app.Fragment
import dagger.Binds
import dagger.Module
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.BaseFragmentModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.favorites.FavoriteContactsPresenter
import io.github.sithengineer.dialer.favorites.FavoriteContactsPresenterImpl
import io.github.sithengineer.dialer.favorites.FavoriteContactsView
import javax.inject.Named

@Module(includes = [(BaseFragmentModule::class)])
abstract class CallHistoryFragmentModule {

  @Binds
  @Named(BaseFragmentModule.FRAGMENT)
  @FragmentScope
  abstract fun fragment(fragment: CallHistoryFragment): Fragment

  @Binds
  @FragmentScope
  abstract fun callHistoryView(view: CallHistoryFragment): FavoriteContactsView

  @Binds
  @FragmentScope
  abstract fun callHistoryPresenter(
      presenter: FavoriteContactsPresenterImpl): FavoriteContactsPresenter

}