package io.github.sithengineer.dialer.favorites

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
import io.github.sithengineer.dialer.abstraction.ui.BaseViewFragment
import io.github.sithengineer.dialer.data.model.Contact

class FavoriteContactsFragment : BaseViewFragment<FavoriteContactsPresenter>(),
    FavoriteContactsView {

  @BindView(R.id.fragment_favorite_contacts_list)
  lateinit var contacts: RecyclerView

  private lateinit var adapter: FavoriteContactsAdapter

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)

    adapter = FavoriteContactsAdapter()
    contacts.adapter = adapter
    contacts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
  }
  
  override fun selectedEditUser() = adapter.userEditSelected()

  override fun selectedToggleFavoriteUser() = adapter.userFavoriteSelected()

  override fun selectedCallUser() = adapter.userCallSelected()

  override fun showUsers(contacts: List<Contact>) {
    adapter.setUsers(contacts)
  }

  override fun removeUser(contact: Contact) {
    adapter.removeUser(contact)
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
    return inflater.inflate(R.layout.fragment_favorite_contacts, container, false)
  }

  companion object {
    fun newInstance(): FavoriteContactsFragment {
      return FavoriteContactsFragment()
    }
  }
}