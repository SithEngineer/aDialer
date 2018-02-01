package io.github.sithengineer.dialer.abstraction.dependencyinjection.components

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
abstract class SchedulersModule {

  @Module
  companion object {

    const val VIEW = "view"
    const val IO = "io"
    const val CPU = "cpu"

    @Provides
    @JvmStatic
    @Named(VIEW)
    fun provideViewScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @JvmStatic
    @Named(IO)
    fun provideIoScheduler() = Schedulers.io()

    @Provides
    @JvmStatic
    @Named(CPU)
    fun provideComputationScheduler() = Schedulers.computation()
  }
}