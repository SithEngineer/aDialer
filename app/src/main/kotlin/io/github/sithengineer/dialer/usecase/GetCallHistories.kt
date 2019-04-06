package io.github.sithengineer.dialer.usecase

import io.github.sithengineer.dialer.abstraction.UseCase
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.usecase.GetCallHistories.Request
import io.github.sithengineer.dialer.usecase.GetCallHistories.Response
import io.github.sithengineer.dialer.viewmodel.CallHistoryViewModel
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetCallHistories @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Request, Response> {

  override fun execute(request: Request): Response {
    return Response(
        Flowable.zip(
            userRepository.getUsers(),
            userRepository.getCallHistories(),
            BiFunction<List<Contact>, List<CallHistory>, List<CallHistoryViewModel>>
            { users, callHistories -> generateCallHistoriesWithUserData(users, callHistories) }
        )
    )
  }

  private fun generateCallHistoriesWithUserData(contacts: List<Contact>,
      callHistories: List<CallHistory>): List<CallHistoryViewModel> {
    val callHistoriesViewModel = mutableListOf<CallHistoryViewModel>()

    callHistories.forEach { entry ->
      callHistoriesViewModel.add(
          CallHistoryViewModel(
              contacts.first { it -> it.id == entry.toUserId }, entry)
      )
    }

    return callHistoriesViewModel
  }

  class Request() : UseCase.RequestValues

  class Response(val callHistories: Flowable<List<CallHistoryViewModel>>) : UseCase.ResponseValue
}