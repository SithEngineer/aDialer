package io.github.sithengineer.dialer.data.source

import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UsersFromPhoneRepository @Inject constructor(

) : UserRepository {
  override fun getUsers(): Single<List<User>> {
    TODO("not implemented")
  }

  override fun insertOrUpdateUsers(vararg user: User): Completable {
    TODO("not implemented")
  }

}