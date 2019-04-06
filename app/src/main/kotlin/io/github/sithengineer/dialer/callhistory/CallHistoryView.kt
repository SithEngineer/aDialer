package io.github.sithengineer.dialer.callhistory

import io.github.sithengineer.dialer.abstraction.ui.View
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.viewmodel.CallHistoryViewModel
import io.reactivex.Observable

interface CallHistoryView : View {
  fun selectedEditUser(): Observable<Contact>
  fun selectedToggleFavoriteUser(): Observable<Contact>
  fun selectedCallUser(): Observable<Contact>
  fun showEditUser(userLookupKey: String)
  fun showCallEndedMessage(userName: String)
  fun showCallHistory(callHistory: List<CallHistoryViewModel>)
  fun addSingleCallHistory(callHistory: CallHistoryViewModel)
}