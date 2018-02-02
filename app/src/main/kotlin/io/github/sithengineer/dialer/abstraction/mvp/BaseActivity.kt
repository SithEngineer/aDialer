package io.github.sithengineer.dialer.abstraction.mvp

import android.app.DialogFragment
import android.app.Fragment
import android.os.Bundle
import android.support.annotation.IdRes
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity

abstract class BaseActivity : DaggerActivity() {

  private var viewUnbinder: Unbinder? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(getContentViewId())
    viewUnbinder = ButterKnife.bind(this)
  }

  abstract fun getContentViewId(): Int

  override fun onDestroy() {
    viewUnbinder?.unbind()
    super.onDestroy()
  }

  protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    fragmentManager.beginTransaction()
        .add(containerViewId, fragment)
        .commit()
  }

  protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    fragmentManager.beginTransaction()
        .replace(containerViewId, fragment)
        .commit()
  }

  protected fun showDialogFragment(dialogFragment: DialogFragment, tag: String) =
      dialogFragment.show(fragmentManager, tag)
}