package io.github.sithengineer.dialer.favorites

import io.github.sithengineer.dialer.abstraction.ui.View
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Observable

interface FavoriteContactsView : View {
  fun selectedToggleFavoriteUser(): Observable<User>
  fun selectedEditUser(): Observable<User>
  fun selectedCallUser(): Observable<User>
  fun showUsers(users: List<User>)
  fun showEditUser(userLookupKey: String)
  fun showCallEndedMessage(userName: String)
  fun removeUser(user: User)
}