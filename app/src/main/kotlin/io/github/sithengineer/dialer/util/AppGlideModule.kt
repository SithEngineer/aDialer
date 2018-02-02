package io.github.sithengineer.dialer.util

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class AppGlideModule : AppGlideModule() {
  override fun isManifestParsingEnabled(): Boolean {
    return false
  }
}