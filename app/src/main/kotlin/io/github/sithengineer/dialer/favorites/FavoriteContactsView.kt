package io.github.sithengineer.dialer.favorites

import io.github.sithengineer.dialer.abstraction.ui.View
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.reactivex.Observable

interface FavoriteContactsView : View {
  fun selectedToggleFavoriteUser(): Observable<Contact>
  fun selectedEditUser(): Observable<Contact>
  fun selectedCallUser(): Observable<Contact>
  fun showUsers(contacts: List<Contact>, contactNumbers: Map<Contact, List<ContactNumber>>)
  fun showEditUser(userLookupKey: String)
  fun showCallEndedMessage(userName: String)
  fun removeUser(contact: Contact)
}