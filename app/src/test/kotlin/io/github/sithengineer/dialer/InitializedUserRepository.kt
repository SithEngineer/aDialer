package io.github.sithengineer.dialer

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.source.InMemoryUserRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class InitializedUserRepository : UserRepository {

  private val userRepository: UserRepository

  init {
    userRepository = InMemoryUserRepository()

    userRepository
        .insertOrUpdateUsers(*DummyData.CONTACTS.toTypedArray())
        .subscribe()

    userRepository
        .insertOrUpdateContactNumbers(*DummyData.contactNumbers.toTypedArray())
        .subscribe()
  }

  override fun getContactsForUser(contact: Contact): Flowable<List<ContactNumber>> =
      userRepository.getContactsForUser(contact)

  override fun getUsers(): Flowable<List<Contact>> =
      userRepository.getUsers()

  override fun insertOrUpdateUsers(vararg contact: Contact): Completable =
      userRepository.insertOrUpdateUsers(*contact)

  override fun insertOrUpdateContactNumbers(vararg contactNumber: ContactNumber): Completable =
      userRepository.insertOrUpdateContactNumbers(*contactNumber)

  override fun insertCallTo(contact: Contact): Single<CallHistory> =
      userRepository.insertCallTo(contact)

  override fun getCallHistories(): Flowable<List<CallHistory>> =
      userRepository.getCallHistories()

}