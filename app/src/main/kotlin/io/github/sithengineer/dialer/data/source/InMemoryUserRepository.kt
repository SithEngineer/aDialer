package io.github.sithengineer.dialer.data.source

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.model.Contact
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.TreeMap

class InMemoryUserRepository : UserRepository {

  private val contactMap: TreeMap<String, Contact> = TreeMap(
      Comparator { id1, id2 -> id1.compareTo(id2, ignoreCase = true) }
  )

  private val userContacts: TreeMap<String, ContactNumber> = TreeMap(
      Comparator { id1, id2 -> id1.compareTo(id2, ignoreCase = true) }
  )

  private val userCallHistory: MutableList<CallHistory> = mutableListOf()

  override fun getUsers(): Flowable<List<Contact>> = Flowable.just(
      contactMap.values.toList())

  override fun insertOrUpdateUsers(vararg contact: Contact): Completable =
      Completable.fromAction {
        contact.forEach {
          contactMap[it.id] = it
        }
      }

  override fun insertOrUpdateContactNumbers(vararg contactNumber: ContactNumber): Completable =
      Completable.fromAction {
        contactNumber.forEach {
          userContacts[it.id] = it
        }
      }

  override fun getContactsForUser(contact: Contact): Flowable<List<ContactNumber>> =
      Flowable
          .fromIterable(userContacts.values)
          .filter { it.contactId == contact.id }
          .toList()
          .toFlowable()

  override fun insertCallTo(contact: Contact): Single<CallHistory> =
      Single
          .just(CallHistory(toUserId = contact.id))
          .doOnSuccess { userCallHistory.add(it) }

  override fun getCallHistories(): Flowable<List<CallHistory>> =
      Flowable.just(userCallHistory)
}