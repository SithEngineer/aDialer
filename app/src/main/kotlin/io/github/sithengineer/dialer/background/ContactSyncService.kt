package io.github.sithengineer.dialer.background

import android.content.Intent
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract.Contacts
import android.support.v4.content.LocalBroadcastManager
import dagger.android.DaggerIntentService
import io.github.sithengineer.dialer.abstraction.ContactsLoadedReceiver
import io.github.sithengineer.dialer.data.UserRepository
import io.github.sithengineer.dialer.data.model.Contact
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

class ContactSyncService : DaggerIntentService("ContactSync_IntentService") {

  companion object {

    private val PROJECTION = arrayOf(Contacts._ID, Contacts.LOOKUP_KEY,
        Contacts.DISPLAY_NAME_PRIMARY, Contacts.PHOTO_THUMBNAIL_URI)

    private const val CONTACT_ID_INDEX = 0
    private const val CONTACT_LOOKUP_KEY_INDEX = 1
    private const val CONTACT_NAME_INDEX = 2
    private const val CONTACT_PHOTO_INDEX = 3
  }

  @Inject
  lateinit var userRepository: UserRepository

  override fun onHandleIntent(intent: Intent?) {
    val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, null,
        null,
        null)

    val phoneNumberUtil = PhoneNumberUtil.createInstance(applicationContext)
    val country = Locale.getDefault().country
    val contacts = mutableListOf<Contact>()
    val contactNumbers = mutableListOf<ContactNumber>()

    if (cursor != null && cursor.moveToFirst()) {
      while (cursor.moveToNext()) {

        val user = Contact(
            id = cursor.getString(CONTACT_ID_INDEX),
            name = cursor.getString(CONTACT_NAME_INDEX),
            lookupKey = cursor.getString(CONTACT_LOOKUP_KEY_INDEX),
            thumbnailPath = if (cursor.isNull(CONTACT_PHOTO_INDEX)) "" else cursor.getString(
                CONTACT_PHOTO_INDEX),
            isFavorite = false)

        val userPhones = mutableListOf<ContactNumber>()

        val phonesCursor = contentResolver.query(Phone.CONTENT_URI, null,
            Phone.CONTACT_ID + " = " + cursor.getString(CONTACT_ID_INDEX), null, null)

        phonesCursor?.let { nonNullPhones ->
          while (nonNullPhones.moveToNext()) {
            val phoneNumber = nonNullPhones.getString(nonNullPhones.getColumnIndex(Phone.NUMBER))
            val parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, country)

            userPhones.add(
                ContactNumber(contactId = user.id, number = phoneNumber,
                    numberType = phoneNumberUtil.getNumberType(parsedPhoneNumber).name)
            )
          }

        }
        phonesCursor?.close()

        if (userPhones.isNotEmpty()) {
          contacts.add(user)
          contactNumbers.addAll(userPhones)
        }
      }
      cursor.close()
    }
    Timber.i("Loaded ${contacts.size} contacts and ${contactNumbers.size} numbers")
    userRepository.insertOrUpdateUsers(*contacts.toTypedArray()).subscribe()
    userRepository.insertOrUpdateContactNumbers(*contactNumbers.toTypedArray()).subscribe()

    // notify rest of the app that contacts where (asynchronously) loaded
    val contactsLoadedIntent = Intent(ContactsLoadedReceiver.ACTION)
    LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(contactsLoadedIntent)
  }

}