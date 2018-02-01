package io.github.sithengineer.dialer.abstraction.mvp

import android.os.Bundle

interface Presenter {
  fun start(state: Bundle?)
  fun resume()
  fun pause()
  fun saveState(): Bundle?
  fun stop()
}