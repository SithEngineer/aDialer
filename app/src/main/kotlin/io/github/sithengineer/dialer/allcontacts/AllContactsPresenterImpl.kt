package io.github.sithengineer.dialer.allcontacts

import android.os.Bundle
import io.github.sithengineer.dialer.mvpabstractions.BasePresenter
import io.github.sithengineer.dialer.scope.FragmentScope
import javax.inject.Inject

@FragmentScope
class AllContactsPresenterImpl @Inject constructor(view: AllContactsView) :
    BasePresenter<AllContactsView>(view), AllContactsPresenter {

  override fun start(state: Bundle?) {
    // TODO
  }

  override fun resume() {
    // TODO
  }

  override fun pause() {
    // TODO
  }

  override fun saveState(): Bundle? {
    return null
  }

  override fun stop() {
    // TODO
  }
}