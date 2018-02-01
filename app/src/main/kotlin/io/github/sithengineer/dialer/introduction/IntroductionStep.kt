package io.github.sithengineer.dialer.introduction

import io.github.sithengineer.dialer.R

enum class IntroductionStep(
    val text: Int
) {
  ONE(R.string.fragment_introduction_text_1),
  TWO(R.string.fragment_introduction_text_2),
  FAILURE(R.string.fragment_introduction_text_failure)
}