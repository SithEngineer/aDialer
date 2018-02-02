package io.github.sithengineer.dialer.usecase.filter

import io.github.sithengineer.dialer.data.model.User

interface UserFilter {
  fun filter(users: List<User>): List<User>
}