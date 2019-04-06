package io.github.sithengineer.dialer.favorites

import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.abstraction.ui.BasePresenter
import io.github.sithengineer.dialer.usecase.CallUser
import io.github.sithengineer.dialer.usecase.GetUsers
import io.github.sithengineer.dialer.usecase.GetUsers.Request
import io.github.sithengineer.dialer.usecase.ToggleFavoriteUser
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@FragmentScope
class FavoriteContactsPresenterImpl @Inject constructor(
    view: FavoriteContactsView,
    private val disposables: CompositeDisposable,
    private val getUsers: GetUsers,
    private val callUser: CallUser,
    private val toggleFavoriteUser: ToggleFavoriteUser,
    @Named(SchedulersModule.VIEW) private val viewScheduler: Scheduler,
    @Named(SchedulersModule.IO) private val ioScheduler: Scheduler
) : BasePresenter<FavoriteContactsView>(view), FavoriteContactsPresenter {

  override fun start(state: Bundle?) {}

  override fun resume() {
    loadContacts()
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
            .subscribe(
                { callHistoryEntry ->
                  view.showCallEndedMessage(callHistoryEntry.contact.name)
                },
                { err ->
                  Timber.e(err)
                }
            )
    )
  }

  private fun handleUserEditSelected() {
    disposables.add(
        view.selectedEditUser()
            .observeOn(viewScheduler)
            .subscribe(
                { user ->
                  view.showEditUser(user.lookupKey)
                },
                { err ->
                  Timber.e(err)
                }
            )
    )
  }

  private fun handleUserFavoriteToggleSelected() {
    disposables.add(
        view.selectedToggleFavoriteUser()
            .flatMapSingle { user ->
              toggleFavoriteUser.execute(
                  ToggleFavoriteUser.Request(user))
                  .updatedContact
                  .subscribeOn(ioScheduler)
            }
            .observeOn(viewScheduler)
            .subscribe(
                { user ->
                  if (!user.isFavorite) {
                    // status was toggled
                    view.removeUser(user)
                  }
                },
                { err ->
                  Timber.e(err)
                }
            )
    )
  }

  private fun loadContacts() {
    val request = Request()
    disposables.add(
        getUsers
            .execute(request)
            .users
            .firstElement()
            .observeOn(viewScheduler)
            .subscribeOn(ioScheduler)
            .subscribe(
                { users ->
                  Timber.v("Favorites contacts showing ${users.size} users")
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