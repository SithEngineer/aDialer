package io.github.sithengineer.dialer.data.source

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.dao.CallHistoryDao
import io.github.sithengineer.dialer.data.dao.ContactDao
import io.github.sithengineer.dialer.data.dao.ContactNumberDao
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class LocalUserRepository @Inject constructor(
    private val contactDao: ContactDao,
    private val callHistoryDao: CallHistoryDao,
    private val contactNumberDao: ContactNumberDao
) : UserRepository {

  override fun getUsers(): Flowable<List<Contact>> = contactDao.getAll()

  override fun insertOrUpdateUsers(vararg contact: Contact): Completable = Completable.fromAction {
    contactDao.insertOrUpdate(*contact)
  }

  override fun insertCallTo(contact: Contact): Single<CallHistory> = Single.fromCallable {
    val entry = CallHistory(toUserId = contact.id)
    callHistoryDao.insert(entry)
    entry
  }

  override fun getCallHistories() = callHistoryDao.getAll()

  override fun insertOrUpdateContactNumbers(
      vararg contactNumber: ContactNumber): Completable = Completable.fromAction {
    contactNumberDao.insertOrUpdate(*contactNumber)
  }

  override fun getContactsForUser(contact: Contact): Flowable<List<ContactNumber>> =
      contactNumberDao.get(contact.id)

}