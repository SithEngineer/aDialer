package io.github.sithengineer.dialer.home

import android.content.SharedPreferences
import android.os.Bundle
import io.github.sithengineer.dialer.mvpabstractions.BasePresenter
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(
    view: HomeView,
    private val preferences: SharedPreferences
) : BasePresenter<HomeView>(view),
    HomePresenter {

  private companion object {
    private const val SHOW_INTRODUCTION = "show_introduction"
  }

  override fun start(state: Bundle?) {
    if(preferences.getBoolean(SHOW_INTRODUCTION, false)){
      view.showIntroduction()
    }else {
      view.showFavoriteContacts()
    }
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