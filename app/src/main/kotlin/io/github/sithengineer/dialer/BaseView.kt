package io.github.sithengineer.dialer

interface BaseView<T : BasePresenter> {
  fun setPresenter(presenter: T)
}