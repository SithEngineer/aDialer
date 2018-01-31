package io.github.sithengineer.dialer.favorites

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjector
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.mvpabstractions.BaseViewFragment

class FavoriteContactsFragment : BaseViewFragment<FavoriteContactsPresenter>(),
    FavoriteContactsView {

  override fun fragmentInjector(): AndroidInjector<Fragment> {
    TODO("not implemented")
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