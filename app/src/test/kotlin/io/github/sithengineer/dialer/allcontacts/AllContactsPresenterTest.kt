package io.github.sithengineer.dialer.allcontacts

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.github.sithengineer.dialer.DummyData
import io.github.sithengineer.dialer.InitializedUserRepository
import io.github.sithengineer.dialer.data.model.User
import io.github.sithengineer.dialer.usecase.CallUser
import io.github.sithengineer.dialer.usecase.GetUsers
import io.github.sithengineer.dialer.usecase.ToggleFavoriteUser
import io.github.sithengineer.dialer.usecase.filter.EmptyUserFilter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class AllContactsPresenterTest : Spek({
  given("a presenter for listing all contacts") {

    val users = DummyData.users

    val publisherSelectedEditUser = PublishSubject.create<User>()
    val publisherSelectedToggleFavoriteUser = PublishSubject.create<User>()
    val publisherSelectedCallUser = PublishSubject.create<User>()

    val view = mock<AllContactsView> {
      on { selectedEditUser() } doReturn publisherSelectedEditUser
      on { selectedToggleFavoriteUser() } doReturn publisherSelectedToggleFavoriteUser
      on { selectedCallUser() } doReturn publisherSelectedCallUser
    }

    val userRepository = spy(InitializedUserRepository())

    val getUsers = spy(GetUsers(userRepository, EmptyUserFilter()))
    val toggleFavoriteUser = spy(ToggleFavoriteUser(userRepository))
    val callUser = spy(CallUser(userRepository))

    val ioScheduler = Schedulers.trampoline()
    val viewScheduler = Schedulers.trampoline()
    val disposables = CompositeDisposable()

    val presenter = AllContactsPresenterImpl(ioScheduler = ioScheduler,
        viewScheduler = viewScheduler, disposables = disposables, view = view, callUser = callUser,
        toggleFavoriteUser = toggleFavoriteUser, getUsers = getUsers)

    on("presenter resumed") {
      presenter.resume()
      it("should load all saved contacts") {
        verify(getUsers, times(1)).execute(any())
        verify(view, times(1)).showUsers(users)
      }
    }

    on("edit contact") {
      publisherSelectedEditUser.onNext(users[0])
      it("should show view to edit contact") {
        verify(view, times(1)).showEditUser(users[0].lookupKey)
      }
    }

    on("toggle favorite contact") {
      publisherSelectedToggleFavoriteUser.onNext(users[0])
      it("should switch favorite user status") {
        verify(toggleFavoriteUser, times(1)).execute(any())
      }
    }

    on("contact selected") {
      publisherSelectedCallUser.onNext(users[0])
      it("should make a call to the selected contact") {
        verify(callUser, times(1)).execute(any())
        verify(view, times(1)).showCallEndedMessage(users[0].name)
      }
    }

    on("presenter paused") {
      it("should have disposed all subscriptions") {
        assert(disposables.isDisposed)
      }
    }
  }
})