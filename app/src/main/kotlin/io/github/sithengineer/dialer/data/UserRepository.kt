package io.github.sithengineer.dialer.data

import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserRepository {
  fun getUsers(): Flowable<List<User>>
  fun insertOrUpdateUsers(vararg user: User): Completable
  fun insertOrUpdateContactNumbers(vararg contactNumber: ContactNumber): Completable
  fun getContactsForUser(user: User): Flowable<List<ContactNumber>>
  fun insertCallTo(user: User): Single<CallHistory>
  fun getCallHistories(): Flowable<List<CallHistory>>
}