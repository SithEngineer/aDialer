package io.github.sithengineer.dialer.callhistory

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseViewFragment
import io.github.sithengineer.dialer.viewmodel.CallHistoryViewModel

class CallHistoryFragment : BaseViewFragment<CallHistoryPresenter>(),
    CallHistoryView {

  @BindView(R.id.fragment_call_history_list)
  lateinit var callHistories: RecyclerView

  private lateinit var adapter: CallHistoryAdapter

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)

    adapter = CallHistoryAdapter()
    callHistories.adapter = adapter
    callHistories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
  }

  override fun selectedEditUser() = adapter.userEditSelected()

  override fun selectedToggleFavoriteUser() = adapter.userFavoriteSelected()

  override fun selectedCallUser() = adapter.userCallSelected()

  override fun showCallHistory(callHistory: List<CallHistoryViewModel>) {
    adapter.setCallHistory(callHistory)
  }

  override fun addSingleCallHistory(callHistory: CallHistoryViewModel) {
    adapter.addCallHistory(callHistory)
  }

  override fun showEditUser(userLookupKey: String) {
    // improve this part using info from:
    // https://developer.android.com/training/contacts-provider/modify-data.html
    val intentInsertEdit = Intent(Intent.ACTION_INSERT_OR_EDIT)
    intentInsertEdit.type = Contacts.CONTENT_ITEM_TYPE
    startActivity(intentInsertEdit)
  }

  override fun showCallEndedMessage(userName: String) {
    Snackbar.make(view!!, resources.getString(R.string.call_with_user_ended, userName),
        Snackbar.LENGTH_LONG).show()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_call_history, container, false)
  }

  companion object {
    fun newInstance(): CallHistoryFragment {
      return CallHistoryFragment()
    }
  }
}