package io.github.sithengineer.dialer.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import butterknife.BindView
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.mvp.BaseActivity
import io.github.sithengineer.dialer.allcontacts.AllContactsFragment
import io.github.sithengineer.dialer.callhistory.CallHistoryFragment
import io.github.sithengineer.dialer.favorites.FavoriteContactsFragment
import io.github.sithengineer.dialer.introduction.IntroductionFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

  @BindView(R.id.activity_home_navigation)
  lateinit var navigation: BottomNavigationView

  @BindView(R.id.activity_home_fragment_placeholder)
  lateinit var placeholder: FrameLayout

  @Inject
  lateinit var presenter: HomePresenterImpl

  private val disposables = CompositeDisposable()
  private val favoritesSelectionPublisher = PublishSubject.create<MenuItem>()
  private val historySelectionPublisher = PublishSubject.create<MenuItem>()
  private val allContactsSelectionPublisher = PublishSubject.create<MenuItem>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
  }

  override fun onStart() {
    super.onStart()
    disposables.add(
        RxBottomNavigationView.itemSelections(navigation).subscribe({ item ->
          when (item.itemId) {
            R.id.navigation_favorites -> {
              showFavoriteContacts()
              favoritesSelectionPublisher.onNext(item)
            }
            R.id.navigation_history -> {
              showCallHistory()
              historySelectionPublisher.onNext(item)
            }
            R.id.navigation_contacts -> {
              showAllContacts()
              allContactsSelectionPublisher.onNext(item)
            }
          }
        })
    )
  }

  override fun onStop() {
    if (!disposables.isDisposed) {
      disposables.dispose()
    }

    super.onStop()
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
    super.onRestoreInstanceState(savedInstanceState)
    presenter.start(savedInstanceState)
  }

  override fun onResume() {
    super.onResume()
    presenter.resume()
  }

  override fun onPause() {
    presenter.pause()
    super.onPause()
  }

  override fun onDestroy() {
    presenter.stop()
    super.onDestroy()
  }

  override fun showIntroduction() {
    addFragment(placeholder.id, IntroductionFragment.newInstance())
  }

  override fun showAllContacts() {
    addFragment(placeholder.id, AllContactsFragment.newInstance())
  }

  override fun showCallHistory() {
    addFragment(placeholder.id, CallHistoryFragment.newInstance())
  }

  override fun showFavoriteContacts() {
    addFragment(placeholder.id, FavoriteContactsFragment.newInstance())
  }

  override fun hideBottomNavigationBar() {
    if (navigation.visibility != View.GONE) {
      navigation.animate().yBy(20F).withEndAction { navigation.visibility = View.GONE }
    }
  }

  override fun showBottomNavigationBar() {
    if (navigation.visibility != View.VISIBLE) {
      navigation.visibility = View.VISIBLE
      navigation.animate().yBy(-20F)
    }
  }
}
