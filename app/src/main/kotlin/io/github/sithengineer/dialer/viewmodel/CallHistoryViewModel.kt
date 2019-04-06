package io.github.sithengineer.dialer.viewmodel

import io.github.sithengineer.dialer.data.model.CallHistory
import io.github.sithengineer.dialer.data.model.Contact

data class CallHistoryViewModel(
    val contact: Contact,
    val callHistory: CallHistory
)