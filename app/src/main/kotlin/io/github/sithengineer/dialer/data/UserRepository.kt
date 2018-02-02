package io.github.sithengineer.dialer.data

import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
  fun getUsers(): Single<List<User>>
  fun insertOrUpdateUsers(vararg user: User): Completable
}