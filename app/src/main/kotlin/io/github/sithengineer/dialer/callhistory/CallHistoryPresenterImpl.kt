package io.github.sithengineer.dialer.callhistory

import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.abstraction.mvp.BasePresenter
import io.github.sithengineer.dialer.favorites.FavoriteContactsPresenter
import io.github.sithengineer.dialer.favorites.FavoriteContactsView
import javax.inject.Inject

@FragmentScope
class CallHistoryPresenterImpl @Inject constructor(view: FavoriteContactsView) :
    BasePresenter<FavoriteContactsView>(view), FavoriteContactsPresenter {

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