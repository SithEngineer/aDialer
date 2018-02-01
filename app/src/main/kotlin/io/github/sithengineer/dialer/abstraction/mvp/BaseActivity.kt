package io.github.sithengineer.dialer.abstraction.mvp

import android.app.Activity
import android.app.DialogFragment
import android.app.Fragment
import android.os.Bundle
import android.support.annotation.IdRes
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : Activity(), HasFragmentInjector {

  private var viewUnbinder: Unbinder? = null

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun fragmentInjector() = fragmentInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onStart() {
    super.onStart()
    viewUnbinder = ButterKnife.bind(this)
  }

  override fun onStop() {
    viewUnbinder?.unbind()
    super.onStop()
  }

  protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    fragmentManager.beginTransaction()
        .add(containerViewId, fragment)
        .commit()
  }

  protected fun showDialogFragment(dialogFragment: DialogFragment, tag: String) =
      dialogFragment.show(fragmentManager, tag)
}