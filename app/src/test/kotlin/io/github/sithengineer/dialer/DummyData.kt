package io.github.sithengineer.dialer

import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.model.User
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil.PhoneNumberType
import java.util.concurrent.ThreadLocalRandom

object DummyData {

  private val RANDOM = ThreadLocalRandom.current()
  private const val TOTAL_USERS = 10
  private const val TOTAL_USER_CONTACTS_PER_USER = 2

  val users: List<User> by lazy {
    (1..TOTAL_USERS).map {
      User(id = "userId$it", name = "User $it", lookupKey = "lookup$it",
          thumbnailPath = "thumbnail_$it")
    }.sortedBy { it.id }
  }

  val contactNumbers: List<ContactNumber> by lazy {
    (1..TOTAL_USERS).map { userId ->
      (1..TOTAL_USER_CONTACTS_PER_USER).map { contactId ->
        ContactNumber(id = "contactId$contactId", userId = "userId$userId",
            number = getRandomPhoneNumber(), numberType = PhoneNumberType.UNKNOWN.name)
      }
    }.flatten().sortedBy { it.id }
  }

  private fun getRandomPhoneNumber(): String {
    // TODO improve this?
    return RANDOM.nextInt().toString()
  }
}