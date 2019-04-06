package io.github.sithengineer.dialer.home

import android.content.SharedPreferences
import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.ui.BasePresenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class HomePresenterImpl @Inject constructor(
    view: HomeView,
    private val preferences: SharedPreferences,
    private val disposables: CompositeDisposable,
    private val contactsLoaded: Observable<Any>,
    @Named(SchedulersModule.VIEW) private val viewScheduler: Scheduler
) : BasePresenter<HomeView>(view),
    HomePresenter {

  private companion object {
    private const val SHOW_INTRODUCTION = "show_introduction"
  }

  override fun start(state: Bundle?) {
    if (preferences.getBoolean(SHOW_INTRODUCTION, true)) {
      view.showIntroduction()
      view.hideBottomNavigationBar()
    } else {
      view.showBottomNavigationBar()
      view.selectFavoriteContacts()
    }

    disposables.add(
        contactsLoaded
            .observeOn(viewScheduler)
            .subscribe(
                { _ ->
                  preferences.edit().putBoolean(SHOW_INTRODUCTION, false).apply()
                  view.showBottomNavigationBar()
                  view.selectAllContacts()
                }
            )
    )
  }

  override fun stop() {
    if (!disposables.isDisposed) {
      disposables.dispose()
    }
  }

}