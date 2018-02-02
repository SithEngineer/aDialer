package io.github.sithengineer.dialer.data

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.ApplicationScope
import io.github.sithengineer.dialer.data.dao.CallHistoryDao
import io.github.sithengineer.dialer.data.dao.UserDao
import io.github.sithengineer.dialer.data.source.UserLocalRepository

@Module
abstract class DatabaseModule {
  @Module
  companion object {

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideDatabase(application: Application): DialerDatabase =
        Room.databaseBuilder(application, DialerDatabase::class.java, "dialer-db")
            .build()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideUserDao(database: DialerDatabase): UserDao = database.getUserDao()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideCallHistoryDao(
        database: DialerDatabase): CallHistoryDao = database.getCallHistoryDao()

    @Provides
    @ApplicationScope
    @JvmStatic
    fun provideUserRepository(userDao: UserDao,
        callHistoryDao: CallHistoryDao): UserRepository = UserLocalRepository(userDao,
        callHistoryDao)
  }

}