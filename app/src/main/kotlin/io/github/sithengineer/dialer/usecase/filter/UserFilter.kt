package io.github.sithengineer.dialer.usecase.filter

import io.github.sithengineer.dialer.data.model.Contact

interface UserFilter {
  fun filter(contacts: List<Contact>): List<Contact>
}