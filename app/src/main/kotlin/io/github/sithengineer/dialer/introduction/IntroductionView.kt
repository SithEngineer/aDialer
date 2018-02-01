package io.github.sithengineer.dialer.introduction

import io.github.sithengineer.dialer.abstraction.mvp.View
import io.reactivex.Observable

interface IntroductionView : View {
  fun nextButtonSelected(): Observable<Any>
  fun syncContacts()
  fun showStep(step: IntroductionStep)
  fun hideButton()
  fun showLoading()
  fun hideLoading()
  fun showButton()
  fun failedToLoadContactsByUserAction(): Observable<Any>
}