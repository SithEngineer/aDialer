package io.github.sithengineer.dialer.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseViewFragment

class FavoriteContactsFragment : BaseViewFragment<FavoriteContactsPresenter>(),
    FavoriteContactsView {

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