package io.github.sithengineer.dialer.background

import android.content.Intent
import android.content.SharedPreferences
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.support.v4.content.LocalBroadcastManager
import dagger.android.DaggerIntentService
import io.github.sithengineer.dialer.abstraction.ContactsLoadedReceiver
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.User
import timber.log.Timber
import javax.inject.Inject

class ContactSyncService : DaggerIntentService("ContactSync_IntentService") {

  companion object {

    private val PROJECTION = arrayOf(Contacts._ID, Contacts.HAS_PHONE_NUMBER, Contacts.LOOKUP_KEY,
        Contacts.DISPLAY_NAME_PRIMARY, Contacts.PHOTO_THUMBNAIL_URI)

    private const val CONTACT_ID_INDEX = 0
    private const val CONTACT_HAS_PHONE_NUMBER_INDEX = 1
    private const val CONTACT_LOOKUP_KEY_INDEX = 2
    private const val CONTACT_NAME_INDEX = 3
    private const val CONTACT_PHOTO_INDEX = 4
  }

  @Inject
  lateinit var userRepository: UserRepository

  override fun onHandleIntent(intent: Intent?) {
    val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, null,
        null,
        null)

    val contacts = mutableListOf<User>()

    if (cursor != null && cursor.moveToFirst()) {
      while (cursor.moveToNext()) {
        if (cursor.getInt(CONTACT_HAS_PHONE_NUMBER_INDEX) == 1) {
          contacts.add(
              User(
                  name = cursor.getString(CONTACT_NAME_INDEX),
                  number = "",
                  thumbnailPath = if(cursor.isNull(CONTACT_PHOTO_INDEX)) "" else cursor.getString(CONTACT_PHOTO_INDEX),
                  isFavorite = false)
          )
        }
      }
      cursor.close()
    }

    Timber.i("Loaded ${contacts.size} contacts")

    userRepository.insertOrUpdateUsers(*contacts.toTypedArray())

    val contactsLoadedIntent = Intent(ContactsLoadedReceiver.ACTION)
    LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(contactsLoadedIntent)
  }

}