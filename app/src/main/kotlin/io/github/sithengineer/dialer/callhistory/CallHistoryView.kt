package io.github.sithengineer.dialer.callhistory

import io.github.sithengineer.dialer.abstraction.ui.View
import io.github.sithengineer.dialer.data.model.User
import io.github.sithengineer.dialer.viewmodel.CallHistoryViewModel
import io.reactivex.Observable

interface CallHistoryView : View {
  fun selectedEditUser(): Observable<User>
  fun selectedToggleFavoriteUser(): Observable<User>
  fun selectedCallUser(): Observable<User>
  fun showEditUser(userLookupKey: String)
  fun showCallEndedMessage(userName: String)
  fun showCallHistory(callHistory: List<CallHistoryViewModel>)
  fun addSingleCallHistory(callHistory: CallHistoryViewModel)
}