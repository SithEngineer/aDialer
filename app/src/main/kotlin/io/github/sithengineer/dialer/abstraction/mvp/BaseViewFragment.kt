package io.github.sithengineer.dialer.abstraction.mvp

import android.os.Bundle
import javax.inject.Inject

abstract class BaseViewFragment<T : Presenter> : BaseFragment(), View {

  @Inject
  protected lateinit var presenter: T

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    presenter.start(savedInstanceState)
  }

  override fun onResume() {
    super.onResume()
    presenter.resume()
  }

  override fun onPause() {
    super.onPause()
    presenter.pause()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    val state = presenter.saveState()
    if (state != null) {
      outState.putAll(state)
    }
  }

  override fun onDestroyView() {
    presenter.stop()
    super.onDestroyView()
  }
}