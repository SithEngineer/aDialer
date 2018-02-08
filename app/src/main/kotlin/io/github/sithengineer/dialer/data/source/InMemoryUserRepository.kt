package io.github.sithengineer.dialer.data.source

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.TreeMap

class InMemoryUserRepository : UserRepository {

  private val userMap: TreeMap<String, User> = TreeMap(
      Comparator { id1, id2 -> id1.compareTo(id2, ignoreCase = true) }
  )

  private val userContacts: TreeMap<String, ContactNumber> = TreeMap(
      Comparator { id1, id2 -> id1.compareTo(id2, ignoreCase = true) }
  )

  private val userCallHistory: MutableList<CallHistory> = mutableListOf()

  override fun getUsers(): Flowable<List<User>> = Flowable.just(
      userMap.values.toList())

  override fun insertOrUpdateUsers(vararg user: User): Completable =
      Completable.fromAction({
        user.forEach {
          userMap[it.id] = it
        }
      })

  override fun insertOrUpdateContactNumbers(vararg contactNumber: ContactNumber): Completable =
      Completable.fromAction({
        contactNumber.forEach {
          userContacts[it.id] = it
        }
      })

  override fun getContactsForUser(user: User): Flowable<List<ContactNumber>> =
      Flowable
          .fromIterable(userContacts.values)
          .filter { it.userId == user.id }
          .toList()
          .toFlowable()

  override fun insertCallTo(user: User): Single<CallHistory> =
      Single
          .just(CallHistory(toUserId = user.id))
          .doOnSuccess { userCallHistory.add(it) }

  override fun getCallHistories(): Flowable<List<CallHistory>> =
      Flowable.just(userCallHistory)
}