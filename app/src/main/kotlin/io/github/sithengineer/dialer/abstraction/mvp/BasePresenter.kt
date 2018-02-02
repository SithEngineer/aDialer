package io.github.sithengineer.dialer.abstraction.mvp

import android.os.Bundle

abstract class BasePresenter<out T : View>(protected val view: T) : Presenter {

  override fun start(state: Bundle?) {
    // does nothing
  }

  override fun resume() {
    // does nothing
  }

  override fun pause() {
    // does nothing
  }

  override fun saveState(): Bundle? {
    return null
  }

  override fun stop() {
    // does nothing
  }
}