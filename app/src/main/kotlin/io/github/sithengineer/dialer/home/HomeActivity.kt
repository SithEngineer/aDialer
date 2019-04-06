package io.github.sithengineer.dialer.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import android.widget.FrameLayout
import butterknife.BindView
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.abstraction.ui.BaseActivity
import io.github.sithengineer.dialer.allcontacts.AllContactsFragment
import io.github.sithengineer.dialer.callhistory.CallHistoryFragment
import io.github.sithengineer.dialer.favorites.FavoriteContactsFragment
import io.github.sithengineer.dialer.introduction.IntroductionFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

  @BindView(R.id.activity_home_navigation)
  lateinit var navigation: BottomNavigationView

  @BindView(R.id.activity_home_fragment_placeholder)
  lateinit var placeholder: FrameLayout

  @Inject
  lateinit var disposables: CompositeDisposable

  @Inject
  lateinit var presenter: HomePresenter

  override fun getContentViewId(): Int = R.layout.activity_home

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    disposables.add(
        RxBottomNavigationView.itemSelections(navigation).subscribe({ item ->
          when (item.itemId) {
            R.id.navigation_favorites -> {
              showFavoriteContactsView()
            }
            R.id.navigation_history -> {
              showCallHistoryView()
            }
            R.id.navigation_all_contacts -> {
              showAllContactsView()
            }
          }
        })
    )
    presenter.start(savedInstanceState)
  }

  override fun onDestroy() {
    presenter.stop()
    if (!disposables.isDisposed) {
      disposables.dispose()
    }
    super.onDestroy()
  }

  override fun onResume() {
    super.onResume()
    presenter.resume()
  }

  override fun onPause() {
    presenter.pause()
    super.onPause()
  }

  override fun showIntroduction() {
    replaceFragment(placeholder.id, IntroductionFragment.newInstance())
  }

  override fun selectAllContacts() {
    navigation.selectedItemId = R.id.navigation_all_contacts
  }

  private fun showAllContactsView() {
    replaceFragment(placeholder.id, AllContactsFragment.newInstance())
  }

  override fun selectCallHistory() {
    navigation.selectedItemId = R.id.navigation_history
  }

  private fun showCallHistoryView() {
    replaceFragment(placeholder.id, CallHistoryFragment.newInstance())
  }

  override fun selectFavoriteContacts() {
    navigation.selectedItemId = R.id.navigation_favorites
  }

  private fun showFavoriteContactsView() {
    replaceFragment(placeholder.id, FavoriteContactsFragment.newInstance())
  }

  override fun hideBottomNavigationBar() {
    if (navigation.visibility != View.GONE) {
      navigation.visibility = View.GONE
    }
  }

  override fun showBottomNavigationBar() {
    if (navigation.visibility != View.VISIBLE) {
      navigation.visibility = View.VISIBLE
    }
  }
}
