package io.github.sithengineer.dialer.data.source

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.dao.CallHistoryDao
import io.github.sithengineer.dialer.data.dao.UserDao
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class UserLocalRepository @Inject constructor(
    private val userDao: UserDao,
    private val callHistoryDao: CallHistoryDao
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
}