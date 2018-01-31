package io.github.sithengineer.dialer.home

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.sithengineer.dialer.allcontacts.AllContactsFragment
import io.github.sithengineer.dialer.allcontacts.AllContactsFragmentModule
import io.github.sithengineer.dialer.callhistory.CallHistoryFragment
import io.github.sithengineer.dialer.callhistory.CallHistoryFragmentModule
import io.github.sithengineer.dialer.favorites.FavoriteContactsFragment
import io.github.sithengineer.dialer.favorites.FavoriteContactsFragmentModule
import io.github.sithengineer.dialer.introduction.IntroductionFragment
import io.github.sithengineer.dialer.introduction.IntroductionFragmentModule
import io.github.sithengineer.dialer.scope.FragmentScope

@Module
abstract class HomeFragmentProvider {

  @FragmentScope
  @ContributesAndroidInjector(modules = [(IntroductionFragmentModule::class)])
  abstract fun introductionFragmentInjector(): IntroductionFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [(AllContactsFragmentModule::class)])
  abstract fun allContactsFragmentInjector(): AllContactsFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [(CallHistoryFragmentModule::class)])
  abstract fun callHistoryFragmentInjector(): CallHistoryFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [(FavoriteContactsFragmentModule::class)])
  abstract fun favoriteContactsFragmentInjector(): FavoriteContactsFragment
}