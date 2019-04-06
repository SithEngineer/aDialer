package io.github.sithengineer.dialer.abstraction.ui

import android.app.DialogFragment
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

  private var viewUnBinder: Unbinder? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(getContentViewId())
    viewUnBinder = ButterKnife.bind(this)
  }

  abstract fun getContentViewId(): Int

  override fun onDestroy() {
    viewUnBinder?.unbind()
    super.onDestroy()
  }

  protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(containerViewId, fragment)
        .commit()
  }

  protected fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerViewId, fragment)
        .commit()
  }

  protected fun showDialogFragment(dialogFragment: DialogFragment, tag: String) =
      dialogFragment.show(fragmentManager, tag)
}