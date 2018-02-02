package io.github.sithengineer.dialer.data.source

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.dao.CallHistoryDao
import io.github.sithengineer.dialer.data.dao.UserDao
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import timber.log.Timber
import javax.inject.Inject

class UserLocalRepository @Inject constructor(
    private val userDao: UserDao,
    private val callHistoryDao: CallHistoryDao
) : UserRepository {

  override fun getUsers(): Flowable<List<User>> = userDao.getAll()

  override fun insertOrUpdateUsers(vararg user: User) = Completable.fromAction {
    userDao.insertOrUpdate(*user)
    Timber.v("Stored locally ${user.size} users")
  }

}