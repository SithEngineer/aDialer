package io.github.sithengineer.dialer.allcontacts

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Contacts
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.R.dimen
import io.github.sithengineer.dialer.abstraction.ui.BaseViewFragment
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.util.ScreenMath
import javax.inject.Inject

class AllContactsFragment : BaseViewFragment<AllContactsPresenter>(), AllContactsView {

  @BindView(R.id.fragment_all_contacts_list)
  lateinit var contacts: RecyclerView

  private lateinit var adapter: AllContactsAdapter

  @Inject
  lateinit var screenMath: ScreenMath

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_all_contacts, container, false)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)

    adapter = AllContactsAdapter()
    contacts.adapter = adapter
    val columns = getNumberOfColumns()
    contacts.layoutManager = StaggeredGridLayoutManager(columns, GridLayoutManager.VERTICAL)
  }

  private fun getNumberOfColumns(): Int {
    val buttonsAreaWidth = 30
    val totalItemWidth = resources.getDimensionPixelSize(
        dimen.list_item_square_contact_size) + buttonsAreaWidth
    return screenMath.calculateNoOfColumns(context!!, totalItemWidth)
  }

  override fun selectedEditUser() = adapter.userEditSelected()

  override fun selectedToggleFavoriteUser() = adapter.userFavoriteSelected()

  override fun selectedCallUser() = adapter.userCallSelected()

  override fun showUsers(contacts: List<Contact>) {
    adapter.setUsers(contacts)
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

  companion object {
    fun newInstance(): AllContactsFragment {
      return AllContactsFragment()
    }
  }
}