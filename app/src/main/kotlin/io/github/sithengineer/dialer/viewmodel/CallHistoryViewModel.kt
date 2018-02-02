package io.github.sithengineer.dialer.viewmodel

import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.User

data class CallHistoryViewModel(
    val user: User,
    val callHistory: CallHistory
)