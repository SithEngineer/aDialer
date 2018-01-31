package io.github.sithengineer.dialer.home

import android.os.Bundle
import android.view.MenuItem
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.R.id
import io.github.sithengineer.dialer.allcontacts.AllContactsFragment
import io.github.sithengineer.dialer.callhistory.CallHistoryFragment
import io.github.sithengineer.dialer.favorites.FavoriteContactsFragment
import io.github.sithengineer.dialer.introduction.IntroductionFragment
import io.github.sithengineer.dialer.mvpabstractions.BaseActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_home.navigation
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

  @Inject
  lateinit var presenter: HomePresenterImpl

  private val disposables = CompositeDisposable()
  private val favoritesSelectionPublisher = PublishSubject.create<MenuItem>()
  private val historySelectionPublisher = PublishSubject.create<MenuItem>()
  private val allContactsSelectionPublisher = PublishSubject.create<MenuItem>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

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

  override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
    super.onRestoreInstanceState(savedInstanceState)
    presenter.start(savedInstanceState)
  }

  override fun onDestroy() {
    presenter.stop()
    super.onDestroy()
  }

  override fun showIntroduction() {
    addFragment(id.fragment_placeholder, IntroductionFragment.newInstance())
  }

  override fun showAllContacts() {
    addFragment(id.fragment_placeholder, AllContactsFragment.newInstance())
  }

  override fun showCallHistory() {
    addFragment(id.fragment_placeholder, CallHistoryFragment.newInstance())
  }

  override fun showFavoriteContacts() {
    addFragment(id.fragment_placeholder, FavoriteContactsFragment.newInstance())
  }
}
