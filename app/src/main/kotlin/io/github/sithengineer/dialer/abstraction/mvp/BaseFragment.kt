package io.github.sithengineer.dialer.abstraction.mvp

import android.app.Activity
import android.app.DialogFragment
import android.app.Fragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasFragmentInjector

abstract class BaseFragment : DialogFragment(), HasFragmentInjector {

  private var viewUnbinder: Unbinder? = null

  override fun fragmentInjector(): AndroidInjector<Fragment> {
    TODO("Not Implemented")
  }

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