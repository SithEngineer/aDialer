package io.github.sithengineer.dialer.abstraction.mvp

import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.DaggerDialogFragment

abstract class BaseFragment : DaggerDialogFragment() {

  private var viewUnbinder: Unbinder? = null

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    // occurs before onResume
    super.onViewStateRestored(savedInstanceState)
    viewUnbinder = ButterKnife.bind(this, view)
  }

  override fun onDestroyView() {
    viewUnbinder?.unbind()
    super.onDestroyView()
  }
}