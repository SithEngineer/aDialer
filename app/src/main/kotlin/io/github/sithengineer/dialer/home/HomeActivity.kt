package io.github.sithengineer.dialer.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import io.github.sithengineer.dialer.R
import io.github.sithengineer.dialer.allcontacts.AllContactsFragment
import io.github.sithengineer.dialer.callhistory.CallHistoryFragment
import io.github.sithengineer.dialer.favorites.FavoriteContactsFragment
import kotlinx.android.synthetic.main.activity_home.navigation

class HomeActivity : AppCompatActivity(), HomeContract.View {

  override fun setPresenter(presenter: HomeContract.Presenter) {
    TODO(
        "not implemented")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    RxBottomNavigationView.itemSelections(navigation).subscribe({ item ->
      when (item.itemId) {
        R.id.navigation_favorites -> {
          supportFragmentManager.beginTransaction().replace(
              R.id.fragment_placeholder,
              FavoriteContactsFragment.newInstance(),
              "all_contacts"
          ).commit()
        }
        R.id.navigation_history -> {
          supportFragmentManager.beginTransaction().replace(
              R.id.fragment_placeholder,
              CallHistoryFragment.newInstance(),
              "all_contacts"
          ).commit()
        }
        R.id.navigation_contacts -> {
          supportFragmentManager.beginTransaction().replace(
              R.id.fragment_placeholder,
              AllContactsFragment.newInstance(),
              "all_contacts"
          ).commit()
        }
      }
    })


    supportFragmentManager.beginTransaction().add(
        R.id.fragment_placeholder,
        AllContactsFragment.newInstance(),
        "all_contacts"
    ).commit()

    /*
    val prefs = getPreferences(Context.MODE_PRIVATE)
    if (prefs.getBoolean("shown_intro", false)) {
      supportFragmentManager.beginTransaction().add(
          R.id.fragment_placeholder,
          AllContactsFragment.newInstance(),
          "all_contacts"
      ).commit()
    } else {
      supportFragmentManager.beginTransaction().add(
          R.id.fragment_placeholder,
          IntroductionFragment.newInstance(),
          "introduction"
      ).commit()
    }
    */
  }
}
