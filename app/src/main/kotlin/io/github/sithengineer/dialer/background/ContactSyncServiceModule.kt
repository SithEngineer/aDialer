package io.github.sithengineer.dialer.background

import android.app.IntentService
import dagger.Binds
import dagger.Module

@Module
interface ContactSyncServiceModule {

  @Binds
  fun provideSyncContactsService(service: ContactSyncService): IntentService

}