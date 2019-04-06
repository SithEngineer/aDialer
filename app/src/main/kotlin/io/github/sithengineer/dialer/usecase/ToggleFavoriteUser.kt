package io.github.sithengineer.dialer.usecase

import io.github.sithengineer.dialer.abstraction.UseCase
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.usecase.ToggleFavoriteUser.Request
import io.github.sithengineer.dialer.usecase.ToggleFavoriteUser.Response
import io.reactivex.Single
import javax.inject.Inject

class ToggleFavoriteUser @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Request, Response> {

  override fun execute(request: Request): Response {
    val userCopy = request.contact.copy(isFavorite = request.contact.isFavorite.not())
    return Response(userRepository.insertOrUpdateUsers(userCopy).toSingleDefault(userCopy))
  }

  class Request(internal val contact: Contact) : UseCase.RequestValues

  class Response(val updatedContact: Single<Contact>) : UseCase.ResponseValue
}