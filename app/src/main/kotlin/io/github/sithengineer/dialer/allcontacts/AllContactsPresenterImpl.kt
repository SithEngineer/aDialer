package io.github.sithengineer.dialer.allcontacts

import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.abstraction.mvp.BasePresenter
import io.github.sithengineer.dialer.usecase.GetUsers
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@FragmentScope
class AllContactsPresenterImpl @Inject constructor(
    view: AllContactsView,
    private val disposables: CompositeDisposable,
    private val getUsers: GetUsers,
    @Named(SchedulersModule.VIEW) private val viewScheduler: Scheduler,
    @Named(SchedulersModule.IO) private val ioScheduler: Scheduler
) : BasePresenter<AllContactsView>(view), AllContactsPresenter {

  override fun start(state: Bundle?) {}

  override fun resume() {
    val request = GetUsers.Request()
    disposables.add(
        getUsers
            .execute(request)
            .users
            .map { data -> data.sortedBy { user -> user.name } }
            .subscribeOn(ioScheduler)
            .observeOn(viewScheduler)
            .subscribe(
                { users ->
                  Timber.v("All contacts showing ${users.size} users")
                  view.showUsers(users)
                },
                { err ->
                  Timber.e(err)
                }
            )
    )
  }

  override fun pause() {
    if (!disposables.isDisposed) {
      disposables.dispose()
    }
  }

  override fun saveState(): Bundle? {
    return null
  }

  override fun stop() {}
}