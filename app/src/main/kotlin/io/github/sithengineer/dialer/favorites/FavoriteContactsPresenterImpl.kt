package io.github.sithengineer.dialer.favorites

import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.abstraction.ui.BasePresenter
import io.github.sithengineer.dialer.usecase.CallUser
import io.github.sithengineer.dialer.usecase.GetContactNumbers
import io.github.sithengineer.dialer.usecase.GetContacts
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
    private val getContacts: GetContacts,
    private val getContactNumbers: GetContactNumbers,
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
    disposables.add(
        getContacts
            .execute(GetContacts.Request())
            .users
            .doOnSuccess { contacts ->
              Timber.v("Showing ${contacts.size} favorite contacts.")
            }
            .flattenAsObservable { contacts -> contacts }
            .flatMapSingle { contact ->
              getContactNumbers.execute(
                  GetContactNumbers.Request(contact)).contactNumbers.map { contactNumbers ->
                Pair(contact, contactNumbers)
              }
            }
            .toList()
            .observeOn(viewScheduler)
            .subscribeOn(ioScheduler)
            .subscribe(
                { contactAndNumbersPairsList ->

                  val contacts = contactAndNumbersPairsList.map { (contact, _) -> contact }

                  val contactNumbers = contactAndNumbersPairsList
                      .map { (_, contactNumbers) -> contactNumbers }
                      .flatten()
                      .groupBy { contactNumbers -> contactNumbers.contactId }
                      .mapKeys { entry -> contacts.first { contact -> contact.id == entry.key } }

                  view.showUsers(contacts, contactNumbers)
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