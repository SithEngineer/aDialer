package io.github.sithengineer.dialer.abstraction.mvp

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  private var viewUnbinder: Unbinder

  init {
    viewUnbinder = ButterKnife.bind(this, view)
  }

}