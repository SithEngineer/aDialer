package io.github.sithengineer.dialer.callhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseViewFragment
import io.github.sithengineer.dialer.favorites.FavoriteContactsPresenter
import io.github.sithengineer.dialer.favorites.FavoriteContactsView

class CallHistoryFragment : BaseViewFragment<FavoriteContactsPresenter>(), FavoriteContactsView {

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