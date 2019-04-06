package io.github.sithengineer.dialer.usecase.filter

import io.github.sithengineer.dialer.data.model.Contact

class EmptyUserFilter : UserFilter {
  override fun filter(contacts: List<Contact>): List<Contact> = contacts
}