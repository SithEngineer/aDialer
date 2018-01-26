package io.github.sithengineer.dialer.home

import io.github.sithengineer.dialer.BasePresenter
import io.github.sithengineer.dialer.BaseView

interface HomeContract {

  interface View : BaseView<Presenter> {

  }

  interface Presenter : BasePresenter {

  }
}