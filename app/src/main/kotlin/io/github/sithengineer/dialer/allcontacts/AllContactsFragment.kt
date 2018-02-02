package io.github.sithengineer.dialer.allcontacts

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseViewFragment
import io.github.sithengineer.dialer.data.model.User
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

    val context = activity
    val buttonsAreaWidth = 30
    val totalItemWidth = resources.getDimensionPixelSize(
        R.dimen.list_item_square_contact_size) + buttonsAreaWidth

    val columns = screenMath.calculateNoOfColumns(context, totalItemWidth)
    contacts.layoutManager = StaggeredGridLayoutManager(columns, GridLayoutManager.VERTICAL)
    contacts.setHasFixedSize(true)
  }

  override fun showUsers(users: List<User>) {
    adapter.setData(users)
  }

  companion object {
    fun newInstance(): AllContactsFragment {
      return AllContactsFragment()
    }
  }
}