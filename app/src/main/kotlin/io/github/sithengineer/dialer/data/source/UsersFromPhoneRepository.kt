package io.github.sithengineer.dialer.data.source

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class UsersFromPhoneRepository @Inject constructor(

) : UserRepository {

  override fun getUsers(): Flowable<List<User>> {
    TODO("not implemented")
  }

  override fun insertOrUpdateUsers(vararg user: User): Completable {
    TODO("not implemented")
  }

  override fun insertCallTo(user: User): Single<CallHistory> {
    TODO("not implemented")
  }

  override fun getCallHistories(): Flowable<List<CallHistory>> {
    TODO("not implemented")
  }
}