package io.github.sithengineer.dialer.introduction

import android.content.SharedPreferences
import android.os.Bundle
import io.github.sithengineer.dialer.abstraction.dependencyinjection.components.SchedulersModule
import io.github.sithengineer.dialer.abstraction.dependencyinjection.scope.FragmentScope
import io.github.sithengineer.dialer.abstraction.ui.BasePresenter
import io.github.sithengineer.dialer.introduction.IntroductionStep.FAILURE
import io.github.sithengineer.dialer.introduction.IntroductionStep.ONE
import io.github.sithengineer.dialer.introduction.IntroductionStep.THREE
import io.github.sithengineer.dialer.introduction.IntroductionStep.TWO
import io.github.sithengineer.dialer.introduction.IntroductionView.PermissionToReadContacts.DENIED
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@FragmentScope
class IntroductionPresenterImpl @Inject constructor(view: IntroductionView,
    private val disposables: CompositeDisposable,
    @Named(SchedulersModule.VIEW) private val viewScheduler: Scheduler,
    private val preferences: SharedPreferences
) : BasePresenter<IntroductionView>(view), IntroductionPresenter {

  companion object {
    const val USER_HAS_SEEN_THE_INTO = "user_has_seen_the_into"
  }

  private lateinit var currentStep: IntroductionStep

  override fun start(state: Bundle?) {
    currentStep = if (preferences.getBoolean(USER_HAS_SEEN_THE_INTO, false)) {
      THREE
    } else {
      ONE
    }
  }

  override fun resume() {
    handleCurrentStep()
    disposables.add(
        view.nextButtonSelected()
            .observeOn(viewScheduler)
            .subscribe({
              currentStep = when (currentStep) {
                ONE -> TWO
                TWO -> THREE
                else -> FAILURE
              }
              handleCurrentStep()
            }, { err -> Timber.e(err) })
    )

    disposables.add(
        view.permissionToReadContacts()
            .observeOn(viewScheduler)
            .subscribe({ result ->
              if (result == DENIED) {
                currentStep = FAILURE
                view.showStep(currentStep)
                view.showButton()
                view.hideLoading()
              }
            }, { error -> Timber.e(error) })
    )
  }

  private fun handleCurrentStep() {
    view.showStep(currentStep)
    if (currentStep == THREE || currentStep == FAILURE) {
      syncContacts()
    }
  }

  private fun syncContacts() {
    view.syncContacts()
    view.hideButton()
    view.showLoading()
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
    preferences.edit().putBoolean(USER_HAS_SEEN_THE_INTO, true).apply()
  }
}