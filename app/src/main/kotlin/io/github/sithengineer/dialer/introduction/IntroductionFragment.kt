package io.github.sithengineer.dialer.introduction

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.sithengineer.dialer.R

class IntroductionFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_introduction, container, false)
  }

  companion object {
    fun newInstance(): IntroductionFragment {
      return IntroductionFragment()
    }
  }
}