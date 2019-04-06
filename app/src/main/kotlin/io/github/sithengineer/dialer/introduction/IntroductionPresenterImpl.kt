package io.github.sithengineer.dialer.introduction

import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.abstraction.ui.BasePresenter
import io.github.sithengineer.dialer.introduction.IntroductionStep.FAILURE
import io.github.sithengineer.dialer.introduction.IntroductionStep.ONE
import io.github.sithengineer.dialer.introduction.IntroductionStep.THREE
import io.github.sithengineer.dialer.introduction.IntroductionStep.TWO
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@FragmentScope
class IntroductionPresenterImpl @Inject constructor(view: IntroductionView,
    private val disposables: CompositeDisposable,
    @Named(SchedulersModule.VIEW) private val viewScheduler: Scheduler
    //,@Named(SchedulersModule.IO) private val ioScheduler: Scheduler
) : BasePresenter<IntroductionView>(view), IntroductionPresenter {

  private lateinit var currentStep: IntroductionStep

  override fun start(state: Bundle?) {
    currentStep = ONE
  }

  override fun resume() {

    view.showStep(currentStep)

    disposables.add(
        view.nextButtonSelected()
            .observeOn(viewScheduler)
            .subscribe({
              when (currentStep) {
                ONE -> {
                  currentStep = TWO
                  view.showStep(currentStep)
                }
                TWO -> {
                  currentStep = THREE
                  view.showStep(currentStep)
                  view.syncContacts()
                  view.hideButton()
                  view.showLoading()
                }
                FAILURE -> {
                  view.syncContacts()
                  view.hideButton()
                  view.showLoading()
                }
                THREE -> {
                  // do nothing
                }
              }
            }, { err -> Timber.e(err) })
    )

    disposables.add(
        view.failedToLoadContactsByUserAction()
            .observeOn(viewScheduler)
            .subscribe({
              currentStep = FAILURE
              view.showStep(currentStep)
              view.showButton()
              view.hideLoading()
            })
    )
  }

  override fun pause() {
    if (!disposables.isDisposed) {
      disposables.dispose()
    }
  }

  override fun saveState(): Bundle? {
    return null
  }

  override fun stop() {

  }
}