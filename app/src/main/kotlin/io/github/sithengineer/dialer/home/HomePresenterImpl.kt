package io.github.sithengineer.dialer.home

import android.content.SharedPreferences
import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.mvp.BasePresenter
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class HomePresenterImpl @Inject constructor(
    view: HomeView,
    private val preferences: SharedPreferences,
    private val disposables: CompositeDisposable,
    private val contactsLoaded: Completable,
    @Named(SchedulersModule.VIEW) private val viewScheduler: Scheduler
) : BasePresenter<HomeView>(view),
    HomePresenter {

  private companion object {
    private const val SHOW_INTRODUCTION = "show_introduction"
  }

  override fun start(state: Bundle?) {
    disposables.add(
        contactsLoaded
            .observeOn(viewScheduler)
            .subscribe(
                {
                  preferences.edit().putBoolean(SHOW_INTRODUCTION, false).apply()
                  view.showBottomNavigationBar()
                  view.showFavoriteContacts()
                }
            )
    )
  }

  override fun resume() {
    if (preferences.getBoolean(SHOW_INTRODUCTION, true)) {
      view.showIntroduction()
      view.hideBottomNavigationBar()
      preferences.edit().putBoolean(SHOW_INTRODUCTION, false).apply()
    } else {
      view.showBottomNavigationBar()
      view.showFavoriteContacts()
    }
  }

  override fun pause() {}

  override fun saveState(): Bundle? {
    return null
  }

  override fun stop() {
    if (!disposables.isDisposed) {
      disposables.dispose()
    }
  }

}