package io.github.sithengineer.dialer.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.sithengineer.dialer.R

class FavoriteContactsFragment : Fragment() {

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