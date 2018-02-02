package io.github.sithengineer.dialer.home

import android.app.Activity
import android.content.IntentFilter
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.sithengineer.dialer.abstraction.ContactsLoadedReceiver
import io.github.sithengineer.dialer.abstraction.RxLocalBroadcastReceiver
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.BaseActivityModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.ActivityScope
import io.reactivex.Observable

@Module(includes = [(BaseActivityModule::class), (HomeFragmentProvider::class)])
abstract class HomeActivityModule {

  @Module
  companion object {
    @Provides
    @ActivityScope
    @JvmStatic
    fun provideContactsLoadedFilter() = IntentFilter(ContactsLoadedReceiver.ACTION)

    @Provides
    @ActivityScope
    @JvmStatic
    fun provideContactsLoadedCompletable(activity: Activity,
        filter: IntentFilter): Observable<Any> {
      return RxLocalBroadcastReceiver.generateObservable(activity,
          filter).map { _ -> Any() }
    }
  }

  @Binds
  @ActivityScope
  abstract fun activity(homeActivity: HomeActivity): Activity

  @Binds
  @ActivityScope
  abstract fun homeView(homeActivity: HomeActivity): HomeView

  @Binds
  @ActivityScope
  abstract fun homePresenter(presenter: HomePresenterImpl): HomePresenter
}