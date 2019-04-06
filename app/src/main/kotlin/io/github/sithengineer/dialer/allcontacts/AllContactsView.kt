package io.github.sithengineer.dialer.allcontacts

import io.github.sithengineer.dialer.abstraction.ui.View
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Observable

interface AllContactsView : View {
  fun selectedEditUser(): Observable<User>
  fun selectedToggleFavoriteUser(): Observable<User>
  fun selectedCallUser(): Observable<User>
  fun showUsers(users: List<User>)
  fun showEditUser(userLookupKey: String)
  fun showCallEndedMessage(userName: String)
}