package io.github.sithengineer.dialer.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.github.sithengineer.dialer.data.dao.CallHistoryDao
import io.github.sithengineer.dialer.data.dao.UserDao
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.User

@Database(
    entities = [User::class, CallHistory::class],
    version = DialerDatabase.DB_VERSION,
    exportSchema = false
)
abstract class DialerDatabase : RoomDatabase() {
  companion object {
    internal const val DB_VERSION = 1
  }

  abstract fun getUserDao(): UserDao

  abstract fun getCallHistoryDao(): CallHistoryDao
}