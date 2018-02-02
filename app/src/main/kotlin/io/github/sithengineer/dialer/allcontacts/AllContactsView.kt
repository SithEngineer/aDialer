package io.github.sithengineer.dialer.allcontacts

import io.github.sithengineer.dialer.abstraction.mvp.View
import io.github.sithengineer.dialer.data.model.User

interface AllContactsView : View {
  fun showUsers(users: List<User>)
}