package io.github.sithengineer.dialer.data.source

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.dao.CallHistoryDao
import io.github.sithengineer.dialer.data.dao.ContactNumberDao
import io.github.sithengineer.dialer.data.dao.UserDao
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class UserLocalRepository @Inject constructor(
    private val userDao: UserDao,
    private val callHistoryDao: CallHistoryDao,
    private val contactNumberDao: ContactNumberDao
) : UserRepository {

  override fun getUsers(): Flowable<List<User>> = userDao.getAll()

  override fun insertOrUpdateUsers(vararg user: User) = Completable.fromAction {
    userDao.insertOrUpdate(*user)
  }

  override fun insertCallTo(user: User) = Single.fromCallable {
    val entry = CallHistory(toUserId = user.id)
    callHistoryDao.insert(entry)
    entry
  }

  override fun getCallHistories() = callHistoryDao.getAll()

  override fun insertOrUpdateContactNumbers(vararg contactNumber: ContactNumber) = Completable.fromAction {
    contactNumberDao.insertOrUpdate(*contactNumber)
  }
}