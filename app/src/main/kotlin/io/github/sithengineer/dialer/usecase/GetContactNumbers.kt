package io.github.sithengineer.dialer.usecase

import io.github.sithengineer.dialer.abstraction.UseCase
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.reactivex.Single
import javax.inject.Inject

class GetContactNumbers @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<GetContactNumbers.Request, GetContactNumbers.Response> {

  override fun execute(request: Request): Response {
    return Response(userRepository.getContactsForUser(request.contact))
  }

  class Request(val contact: Contact) : UseCase.RequestValues

  class Response(val contactNumbers: Single<List<ContactNumber>>) : UseCase.ResponseValue
}