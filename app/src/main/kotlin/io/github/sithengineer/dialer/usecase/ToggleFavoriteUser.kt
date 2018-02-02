package io.github.sithengineer.dialer.usecase

import io.github.sithengineer.dialer.abstraction.UseCase
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.User
import io.github.sithengineer.dialer.usecase.ToggleFavoriteUser.Request
import io.github.sithengineer.dialer.usecase.ToggleFavoriteUser.Response
import io.reactivex.Single
import javax.inject.Inject

class ToggleFavoriteUser @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Request, Response> {

  override fun execute(request: Request): Response {
    val user = request.user
    user.isFavorite = !user.isFavorite
    return Response(userRepository.insertOrUpdateUsers(user).toSingleDefault(user))
  }

  class Request(internal val user: User) : UseCase.RequestValues

  class Response(val updatedUser: Single<User>) : UseCase.ResponseValue
}