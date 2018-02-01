package io.github.sithengineer.dialer.abstraction

import android.content.BroadcastReceiver

abstract class ContactsLoadedReceiver : BroadcastReceiver() {

  companion object {
    const val ACTION = "LOAD_CONTACTS_ACTION"
  }

}