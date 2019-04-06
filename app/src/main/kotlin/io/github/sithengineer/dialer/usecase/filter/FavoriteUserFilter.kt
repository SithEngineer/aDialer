package io.github.sithengineer.dialer.usecase.filter

import io.github.sithengineer.dialer.data.model.Contact

class FavoriteUserFilter : UserFilter {
  override fun filter(contacts: List<Contact>): List<Contact> = contacts.filter { user -> user.isFavorite }
}