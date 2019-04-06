package io.github.sithengineer.dialer.data

import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserRepository {
  fun getUsers(): Single<List<Contact>>
  fun insertOrUpdateUsers(vararg contact: Contact): Completable
  fun insertOrUpdateContactNumbers(vararg contactNumber: ContactNumber): Completable
  fun getContactsForUser(contact: Contact): Single<List<ContactNumber>>
  fun insertCallTo(contact: Contact): Single<CallHistory>
  fun getCallHistories(): Single<List<CallHistory>>
}