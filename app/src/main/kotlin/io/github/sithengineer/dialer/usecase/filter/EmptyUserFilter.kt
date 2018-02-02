package io.github.sithengineer.dialer.usecase.filter

import io.github.sithengineer.dialer.data.model.User

class EmptyUserFilter : UserFilter {
  override fun filter(users: List<User>): List<User> = users
}