package io.github.sithengineer.dialer.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpacingItemDecoration(
    private val spaceInPx: Int,
    private val isVertical: Boolean = true,
    private val isHorizontal: Boolean = true
) : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
      state: RecyclerView.State) {
    val position = parent.getChildViewHolder(view).adapterPosition
    val itemCount = state.itemCount

    outRect.left = spaceInPx
    outRect.top = spaceInPx
    outRect.right = if (isHorizontal && position == itemCount - 1) spaceInPx else 0
    outRect.bottom = if (isVertical && position == itemCount - 1) spaceInPx else 0
  }
}