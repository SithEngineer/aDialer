package io.github.sithengineer.dialer.home

import io.github.sithengineer.dialer.abstraction.ui.View

interface HomeView : View {
  fun selectAllContacts()
  fun selectCallHistory()
  fun selectFavoriteContacts()
  fun showIntroduction()
  fun hideBottomNavigationBar()
  fun showBottomNavigationBar()
}