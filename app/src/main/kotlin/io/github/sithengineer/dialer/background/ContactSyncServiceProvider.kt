package io.github.sithengineer.dialer.background

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContactSyncServiceProvider {

  @ContributesAndroidInjector(modules = [(ContactSyncServiceModule::class)])
  abstract fun contactSyncServiceInjector(): ContactSyncService

}