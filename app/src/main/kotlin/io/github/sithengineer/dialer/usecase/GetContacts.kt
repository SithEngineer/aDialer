package io.github.sithengineer.dialer.usecase

import io.github.sithengineer.dialer.abstraction.UseCase
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.usecase.filter.UserFilter
import io.reactivex.Single
import javax.inject.Inject

class GetContacts @Inject constructor(
    private val userRepository: UserRepository,
    private val userFilter: UserFilter
) : UseCase<GetContacts.Request, GetContacts.Response> {

  override fun execute(request: Request): Response {
    return Response(userRepository.getUsers().map { userFilter.filter(it) })
  }

  class Request : UseCase.RequestValues

  class Response(val users: Single<List<Contact>>) : UseCase.ResponseValue
}