package io.github.sithengineer.dialer.callhistory

import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.abstraction.ui.BasePresenter
import io.github.sithengineer.dialer.usecase.CallUser
import io.github.sithengineer.dialer.usecase.GetCallHistories
import io.github.sithengineer.dialer.usecase.GetCallHistories.Request
import io.github.sithengineer.dialer.usecase.ToggleFavoriteUser
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@FragmentScope
class CallHistoryPresenterImpl @Inject constructor(
    view: CallHistoryView,
    private val disposables: CompositeDisposable,
    private val getCallHistories: GetCallHistories,
    private val callUser: CallUser,
    private val toggleFavoriteUser: ToggleFavoriteUser,
    @Named(SchedulersModule.VIEW) private val viewScheduler: Scheduler,
    @Named(SchedulersModule.IO) private val ioScheduler: Scheduler
) : BasePresenter<CallHistoryView>(view), CallHistoryPresenter {

  override fun start(state: Bundle?) {}

  override fun resume() {
    loadCallHistories()
    handleUserFavoriteToggleSelected()
    handleUserEditSelected()
    handleUserCallSelected()
  }

  private fun handleUserCallSelected() {
    disposables.add(
        view.selectedCallUser()
            .flatMapSingle { user ->
              callUser.execute(CallUser.Request(user))
                  .callEnded
                  .subscribeOn(ioScheduler)
            }
            .observeOn(viewScheduler)
            .subscribe({ callHistoryEntry ->
              view.showCallEndedMessage(callHistoryEntry.user.name)
            })
    )
  }

  private fun handleUserEditSelected() {
    disposables.add(
        view.selectedEditUser()
            .observeOn(viewScheduler)
            .subscribe({ user ->
              view.showEditUser(user.lookupKey)
            })
    )
  }

  private fun handleUserFavoriteToggleSelected() {
    disposables.add(
        view.selectedToggleFavoriteUser()
            .flatMapSingle { user ->
              toggleFavoriteUser.execute(
                  ToggleFavoriteUser.Request(user))
                  .updatedUser
                  .subscribeOn(ioScheduler)
            }
            .observeOn(viewScheduler)
            .subscribe()
    )
  }

  private fun loadCallHistories() {
    val request = Request()
    disposables.add(
        getCallHistories
            .execute(request)
            .callHistories
            .observeOn(viewScheduler)
            .subscribeOn(ioScheduler)
            .subscribe(
                { callHistories ->
                  Timber.v("Call history showing ${callHistories.size} entries")
                  view.showCallHistory(callHistories)
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