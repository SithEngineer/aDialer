package io.github.sithengineer.dialer.mvpabstractions

import android.os.Bundle

abstract class BasePresenter<out T : View>(protected val view: T) : Presenter