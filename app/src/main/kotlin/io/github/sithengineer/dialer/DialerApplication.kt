package io.github.sithengineer.dialer

import android.app.Activity
import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.timber.StethoTree
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.sithengineer.dialer.dependencyinjection.DaggerAppComponent
import io.github.sithengineer.dialer.log.CrashReportTree
import timber.log.Timber
import javax.inject.Inject

class DialerApplication : Application(), HasActivityInjector {

  @Inject
  lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun activityInjector() = activityInjector

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent
        .builder()
        .create(this)
        .inject(this)

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