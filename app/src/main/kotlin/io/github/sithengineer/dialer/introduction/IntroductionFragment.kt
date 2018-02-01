package io.github.sithengineer.dialer.introduction

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import com.jakewharton.rxbinding2.view.RxView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseViewFragment
import io.github.sithengineer.dialer.background.ContactSyncService
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class IntroductionFragment : BaseViewFragment<IntroductionPresenter>(), IntroductionView {

  @BindView(R.id.fragment_introduction_loading)
  lateinit var loading: ProgressBar

  @BindView(R.id.fragment_introduction_text)
  lateinit var instructions: TextView

  @BindView(R.id.fragment_introduction_fab_button)
  lateinit var fab: FloatingActionButton

  lateinit var failedToLoadContactsSubject: PublishSubject<Any>

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_introduction, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    failedToLoadContactsSubject = PublishSubject.create()
  }

  override fun failedToLoadContactsByUserAction(): Observable<Any> {
    return failedToLoadContactsSubject
  }

  override fun nextButtonSelected(): Observable<Any> {
    return RxView.clicks(fab).debounce(400, TimeUnit.MILLISECONDS)
  }

  override fun syncContacts() {
    val readContactsPermission = ActivityCompat.checkSelfPermission(activity,
        Manifest.permission.READ_CONTACTS)
    val writeContactsPermission = ActivityCompat.checkSelfPermission(activity,
        Manifest.permission.READ_CONTACTS)

    if (readContactsPermission == writeContactsPermission && readContactsPermission == PackageManager.PERMISSION_GRANTED) {
      readContacts(activity)
    } else {
      ActivityCompat.requestPermissions(activity,
          arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS),
          CONTACTS_REQUEST_CODE)
    }
  }

  override fun showStep(step: IntroductionStep) {
    instructions.text = getString(step.text)
  }

  private fun readContacts(context: Context) {
    val syncContacts = Intent(context, ContactSyncService::class.java)
    context.startService(syncContacts)
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
      grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == CONTACTS_REQUEST_CODE) {
      if (grantResults.isEmpty() ||
          (grantResults[0] != PackageManager.PERMISSION_GRANTED && grantResults[1] != PackageManager.PERMISSION_GRANTED)) {
        Timber.e("user did NOT give permission to read contacts.")
      } else {
        readContacts(activity)
      }
    }
  }

  override fun showButton() {
    fab.visibility = View.VISIBLE
    fab.animate().scaleX(1F).scaleY(1F)
  }

  override fun hideButton() {
    fab.animate().scaleX(0F).scaleY(0F).withEndAction { fab.visibility = View.GONE }
  }

  override fun showLoading() {
    loading.visibility = View.VISIBLE
  }

  override fun hideLoading() {
    loading.visibility = View.GONE
  }

  companion object {
    const val CONTACTS_REQUEST_CODE = 12

    fun newInstance(): IntroductionFragment {
      return IntroductionFragment()
    }
  }
}