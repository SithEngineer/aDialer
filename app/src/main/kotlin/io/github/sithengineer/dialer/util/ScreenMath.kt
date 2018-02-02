package io.github.sithengineer.dialer.util

import android.content.Context

class ScreenMath {

  fun convertDpToPixel(context: Context, dp: Int): Int {
    val density = context.resources.displayMetrics.density
    return Math.round(dp.toFloat() * density)
  }

  fun calculateNoOfColumns(context: Context, itemWidthInPixels: Int): Int {
    val screenWidth = context.resources.displayMetrics.widthPixels
    val columns = (screenWidth / itemWidthInPixels)
    return if (columns > 0) columns else 1
  }
}