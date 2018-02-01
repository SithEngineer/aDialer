package io.github.sithengineer.dialer.introduction

import android.app.Fragment
import dagger.Binds
import dagger.Module
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.BaseFragmentModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import javax.inject.Named

@Module(includes = [(BaseFragmentModule::class)])
abstract class IntroductionFragmentModule {

  @Binds
  @Named(BaseFragmentModule.FRAGMENT)
  @FragmentScope
  abstract fun fragment(introductionFragment: IntroductionFragment): Fragment

  @Binds
  @FragmentScope
  abstract fun introductionView(view: IntroductionFragment): IntroductionView

  @Binds
  @FragmentScope
  abstract fun introductionPresenter(
      presenter: IntroductionPresenterImpl): IntroductionPresenter

}