package io.github.sithengineer.dialer.allcontacts

import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.abstraction.mvp.BasePresenter
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