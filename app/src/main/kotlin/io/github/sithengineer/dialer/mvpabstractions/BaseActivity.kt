package io.github.sithengineer.dialer.mvpabstractions

import android.app.Activity
import android.app.DialogFragment
import android.app.Fragment
import android.os.Bundle
import android.support.annotation.IdRes
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : Activity(), HasFragmentInjector {

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun fragmentInjector() = fragmentInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    fragmentManager.beginTransaction()
        .add(containerViewId, fragment)
        .commit()
  }

  protected fun showDialogFragment(dialogFragment: DialogFragment, tag: String) =
      dialogFragment.show(fragmentManager, tag)
}