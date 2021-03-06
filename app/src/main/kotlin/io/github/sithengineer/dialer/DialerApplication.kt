package io.github.sithengineer.dialer

import android.os.StrictMode
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.DaggerAppComponent
import io.github.sithengineer.dialer.util.CrashReportTree
import timber.log.Timber

class DialerApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().create(this)
  }

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent
        .builder()
        .create(this)
        .inject(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
      // TODO enable this
      //enableStrictMode()
    } else {
      Timber.plant(CrashReportTree())
    }
  }

  private fun enableStrictMode() {
    StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()
        .detectDiskWrites()
        .detectNetwork()
        .penaltyLog()
        .build())

    StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
        .penaltyDeath()
        .build())
  }
}