package io.github.sithengineer.dialer

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.model.User
import io.github.sithengineer.dialer.data.source.InMemoryUserRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class InitializedUserRepository : UserRepository {

  private val userRepository: UserRepository

  init {
    userRepository = InMemoryUserRepository()

    userRepository
        .insertOrUpdateUsers(*DummyData.users.toTypedArray())
        .subscribe()

    userRepository
        .insertOrUpdateContactNumbers(*DummyData.contactNumbers.toTypedArray())
        .subscribe()
  }

  override fun getContactsForUser(user: User): Flowable<List<ContactNumber>> =
      userRepository.getContactsForUser(user)

  override fun getUsers(): Flowable<List<User>> =
      userRepository.getUsers()

  override fun insertOrUpdateUsers(vararg user: User): Completable =
      userRepository.insertOrUpdateUsers(*user)

  override fun insertOrUpdateContactNumbers(vararg contactNumber: ContactNumber): Completable =
      userRepository.insertOrUpdateContactNumbers(*contactNumber)

  override fun insertCallTo(user: User): Single<CallHistory> =
      userRepository.insertCallTo(user)

  override fun getCallHistories(): Flowable<List<CallHistory>> =
      userRepository.getCallHistories()

}