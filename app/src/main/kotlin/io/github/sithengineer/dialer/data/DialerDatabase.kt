package io.github.sithengineer.dialer.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.github.sithengineer.dialer.data.dao.CallHistoryDao
import io.github.sithengineer.dialer.data.dao.ContactNumberDao
import io.github.sithengineer.dialer.data.dao.ContactDao
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.github.sithengineer.dialer.data.model.Contact

@Database(
    entities = [Contact::class, CallHistory::class, ContactNumber::class],
    version = DialerDatabase.DB_VERSION,
    exportSchema = false
)
abstract class DialerDatabase : RoomDatabase() {
  companion object {
    internal const val DB_VERSION = 2
  }

  abstract fun getContactDao(): ContactDao

  abstract fun getCallHistoryDao(): CallHistoryDao

  abstract fun getContactNumberDao(): ContactNumberDao
}