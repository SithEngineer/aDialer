package io.github.sithengineer.dialer.math

import android.content.Context

class ScreenMath {

  fun getScreenWidthInDps(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    return dpWidth.toInt()
  }

  fun calculateNoOfColumns(context: Context, itemWidth: Int): Int {
    val screenWidth = getScreenWidthInDps(context)
    return (screenWidth / itemWidth)
  }
}