package io.github.sithengineer.dialer.background

import android.app.IntentService
import android.content.Intent
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.support.v4.content.LocalBroadcastManager
import io.github.sithengineer.dialer.abstraction.ContactsLoadedReceiver
import timber.log.Timber

class ContactSyncService : IntentService("ContactSync_IntentService") {
  override fun onHandleIntent(intent: Intent?) {
    val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null,
        null)
    if (cursor != null && cursor.moveToFirst()) {

      val nameColumn = Contacts.DISPLAY_NAME_PRIMARY

      while (cursor.moveToNext()) {
        val hasPhone = Integer.parseInt(cursor.getString(
            cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))
        if (hasPhone == 1) {
          val contactName = cursor.getString(cursor.getColumnIndex(nameColumn))
          Timber.i("Contact name: $contactName")
          // TODO add contacts to local DB
        }
      }
      cursor.close()
    }

    val contactsLoadedIntent = Intent(ContactsLoadedReceiver.ACTION)
    LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(contactsLoadedIntent)
  }

}