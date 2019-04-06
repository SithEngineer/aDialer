package io.github.sithengineer.dialer.data

import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.model.Contact
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserRepository {
  fun getUsers(): Flowable<List<Contact>>
  fun insertOrUpdateUsers(vararg contact: Contact): Completable
  fun insertOrUpdateContactNumbers(vararg contactNumber: ContactNumber): Completable
  fun getContactsForUser(contact: Contact): Flowable<List<ContactNumber>>
  fun insertCallTo(contact: Contact): Single<CallHistory>
  fun getCallHistories(): Flowable<List<CallHistory>>
}