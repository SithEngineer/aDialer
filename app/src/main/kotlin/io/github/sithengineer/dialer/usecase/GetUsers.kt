package io.github.sithengineer.dialer.usecase

import io.github.sithengineer.dialer.abstraction.UseCase
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.usecase.filter.UserFilter
import io.reactivex.Flowable
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val userRepository: UserRepository,
    private val userFilter: UserFilter
) : UseCase<GetUsers.Request, GetUsers.Response> {

  override fun execute(request: Request): Response {
    val rxResponse = userRepository.getUsers()
    return Response(rxResponse.map { it -> userFilter.filter(it) })
  }

  class Request : UseCase.RequestValues

  class Response(val users: Flowable<List<Contact>>) : UseCase.ResponseValue
}