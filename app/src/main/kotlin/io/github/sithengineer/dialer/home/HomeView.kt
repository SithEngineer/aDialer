package io.github.sithengineer.dialer.home

import io.github.sithengineer.dialer.abstraction.mvp.View

interface HomeView : View {
  fun showAllContacts()
  fun showCallHistory()
  fun showFavoriteContacts()
  fun showIntroduction()
  fun hideBottomNavigationBar()
  fun showBottomNavigationBar()
}