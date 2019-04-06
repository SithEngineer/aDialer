package io.github.sithengineer.dialer.data

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.ApplicationScope
import io.github.sithengineer.dialer.data.dao.CallHistoryDao
import io.github.sithengineer.dialer.data.dao.ContactNumberDao
import io.github.sithengineer.dialer.data.dao.ContactDao
import io.github.sithengineer.dialer.data.source.LocalUserRepository

@Module
abstract class DatabaseModule {
  @Module
  companion object {

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideDatabase(application: Application): DialerDatabase =
        Room.databaseBuilder(application, DialerDatabase::class.java, "dialer-db")
            .fallbackToDestructiveMigration() // TODO improve migrations between versions
            .build()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideUserDao(database: DialerDatabase): ContactDao = database.getContactDao()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideCallHistoryDao(
        database: DialerDatabase): CallHistoryDao = database.getCallHistoryDao()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideContactNumberDao(
        database: DialerDatabase): ContactNumberDao = database.getContactNumberDao()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideUserRepository(contactDao: ContactDao,
        callHistoryDao: CallHistoryDao, contactNumberDao: ContactNumberDao): UserRepository =
        LocalUserRepository(contactDao, callHistoryDao, contactNumberDao)
  }

}