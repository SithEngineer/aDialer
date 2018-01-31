package io.github.sithengineer.dialer.mvpabstractions

import android.app.Activity
import android.app.DialogFragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.AndroidInjection
import dagger.android.HasFragmentInjector

abstract class BaseFragment : DialogFragment(), HasFragmentInjector {

  private var viewUnbinder: Unbinder? = null

  @Suppress("DEPRECATION")
  @SuppressWarnings("DEPRECATION")
  override fun onAttach(activity: Activity) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      AndroidInjection.inject(this)
    }
    super.onAttach(activity)
  }

  override fun onAttach(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      AndroidInjection.inject(this)
    }
    super.onAttach(context)
  }

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