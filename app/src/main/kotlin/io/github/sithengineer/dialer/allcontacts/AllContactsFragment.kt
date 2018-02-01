package io.github.sithengineer.dialer.allcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseViewFragment

class AllContactsFragment : BaseViewFragment<AllContactsPresenter>(), AllContactsView {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_all_contacts, container, false)
  }

  companion object {
    fun newInstance(): AllContactsFragment {
      return AllContactsFragment()
    }
  }
}