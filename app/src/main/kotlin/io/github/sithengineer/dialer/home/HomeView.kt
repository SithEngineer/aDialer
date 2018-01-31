package io.github.sithengineer.dialer.home

import io.github.sithengineer.dialer.mvpabstractions.View

interface HomeView : View {
  fun showAllContacts()
  fun showCallHistory()
  fun showFavoriteContacts()
  fun showIntroduction()
}