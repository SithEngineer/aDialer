package io.github.sithengineer.dialer.allcontacts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.sithengineer.dialer.R
import kotlinx.android.synthetic.main.fragment_all_contacts.*

class AllContactsFragment : Fragment() {

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