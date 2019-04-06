package io.github.sithengineer.dialer.abstraction.ui

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.DaggerAppCompatDialogFragment

abstract class BaseFragment : DaggerAppCompatDialogFragment() {

  private var viewUnbinder: Unbinder? = null

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    // occurs before onResume
    super.onViewStateRestored(savedInstanceState)
    view?.let {
      viewUnbinder = ButterKnife.bind(this, it)
    }
  }

  override fun onDestroyView() {
    viewUnbinder?.unbind()
    super.onDestroyView()
  }
}