package io.github.sithengineer.dialer.usecase

import dagger.Module
import dagger.Provides
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.usecase.filter.UserFilter

@Module
abstract class UseCaseModule {

  @Module
  companion object {
    @Provides
    @JvmStatic
    fun getUsers(userRepository: UserRepository, filter: UserFilter) = GetUsers(userRepository,
        filter)

    @Provides
    @JvmStatic
    fun getCallUser(userRepository: UserRepository) = CallUser(userRepository)

    @Provides
    @JvmStatic
    fun getToggleFavoriteUser(userRepository: UserRepository) = ToggleFavoriteUser(userRepository)

    @Provides
    @JvmStatic
    fun getCallHistories(userRepository: UserRepository) = GetCallHistories(userRepository)
  }
}