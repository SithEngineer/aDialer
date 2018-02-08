package io.github.sithengineer.dialer.usecase

import io.github.sithengineer.dialer.abstraction.UseCase
import io.github.sithengineer.dialer.viewmodel.CallHistoryViewModel
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.User
import io.github.sithengineer.dialer.usecase.CallUser.Request
import io.github.sithengineer.dialer.usecase.CallUser.Response
import io.reactivex.Single
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject

class CallUser @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Request, Response> {
  override fun execute(request: Request): Response {
    return Response(
        userRepository
            .insertCallTo(request.user)
            .map { callHistory ->
              CallHistoryViewModel(request.user,
                  callHistory)
            })
  }

  class Request(internal val user: User) : UseCase.RequestValues

  class Response(val callEnded: Single<CallHistoryViewModel>) : UseCase.ResponseValue
}