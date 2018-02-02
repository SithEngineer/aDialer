package io.github.sithengineer.dialer

import android.content.Context
import com.facebook.stetho.Stetho
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

    if (BuildConfig.DEBUG) {
      //initializeStetho(applicationContext)
      //Timber.plant(StethoTree())
      Timber.plant(Timber.DebugTree())
    } else {
      Timber.plant(CrashReportTree())
    }

    DaggerAppComponent
        .builder()
        .create(this)
        .inject(this)
  }

  private fun initializeStetho(context: Context) {
    val initializer = Stetho.newInitializerBuilder(context)
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
        .build()

    Stetho.initialize(initializer)
  }
}