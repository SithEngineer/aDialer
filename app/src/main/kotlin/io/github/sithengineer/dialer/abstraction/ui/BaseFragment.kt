package io.github.sithengineer.dialer.abstraction.ui

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.DaggerAppCompatDialogFragment

abstract class BaseFragment : DaggerAppCompatDialogFragment() {

  private var viewUnBinder: Unbinder? = null

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    // occurs before onResume
    super.onViewStateRestored(savedInstanceState)
    view?.let {
      viewUnBinder = ButterKnife.bind(this, it)
    }
  }

  override fun onDestroyView() {
    viewUnBinder?.unbind()
    super.onDestroyView()
  }
}