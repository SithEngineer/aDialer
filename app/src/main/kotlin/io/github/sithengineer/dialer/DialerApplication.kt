package io.github.sithengineer.dialer

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.timber.StethoTree
import io.github.sithengineer.dialer.log.CrashReportTree
import timber.log.Timber

class DialerApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      initializeStetho(applicationContext)
    } else {
      Timber.plant(CrashReportTree())
    }
  }

  private fun initializeStetho(context: Context) {
    val initializer = Stetho.newInitializerBuilder(context)
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
        .build()

    Stetho.initialize(initializer)

    Timber.plant(StethoTree())
  }
}