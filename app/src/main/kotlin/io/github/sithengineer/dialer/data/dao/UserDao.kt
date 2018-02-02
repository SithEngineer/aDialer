package io.github.sithengineer.dialer.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.github.sithengineer.dialer.data.model.User
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {

  @Query("SELECT * FROM user")
  fun getAll(): Flowable<List<User>>

  @Query("SELECT * FROM user WHERE userId = :id")
  fun get(id: String): Single<User>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdate(vararg users: User)

  @Delete
  fun delete(user: User)

}