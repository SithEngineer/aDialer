package io.github.sithengineer.dialer.abstraction.mvp

abstract class BasePresenter<out T : View>(protected val view: T) : Presenter