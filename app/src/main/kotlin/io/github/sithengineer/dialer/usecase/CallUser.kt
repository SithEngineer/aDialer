package io.github.sithengineer.dialer.usecase

import io.github.sithengineer.dialer.abstraction.UseCase
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.usecase.CallUser.Request
import io.github.sithengineer.dialer.usecase.CallUser.Response
import io.github.sithengineer.dialer.viewmodel.CallHistoryViewModel
import io.reactivex.Single
import javax.inject.Inject

class CallUser @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Request, Response> {
  override fun execute(request: Request): Response {
    return Response(
        userRepository
            .insertCallTo(request.contact)
            .map { callHistory ->
              CallHistoryViewModel(request.contact,
                  callHistory)
            })
  }

  class Request(internal val contact: Contact) : UseCase.RequestValues

  class Response(val callEnded: Single<CallHistoryViewModel>) : UseCase.ResponseValue
}