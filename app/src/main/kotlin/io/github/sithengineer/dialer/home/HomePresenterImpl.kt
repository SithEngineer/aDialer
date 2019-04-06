package io.github.sithengineer.dialer.home

import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.ui.BasePresenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class HomePresenterImpl @Inject constructor(
    view: HomeView,
    private val disposables: CompositeDisposable,
    private val contactsLoaded: Observable<Any>,
    @Named(SchedulersModule.VIEW) private val viewScheduler: Scheduler
) : BasePresenter<HomeView>(view),
    HomePresenter {

  override fun start(state: Bundle?) {
    view.showIntroduction()
    view.hideBottomNavigationBar()
    disposables.add(
        contactsLoaded
            .observeOn(viewScheduler)
            .subscribe({
              view.showBottomNavigationBar()
              view.selectAllContacts()
            }, { error -> Timber.e(error) })
    )
  }

  override fun stop() {
    if (!disposables.isDisposed) {
      disposables.dispose()
    }
  }

}