package io.github.sithengineer.dialer.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.github.sithengineer.dialer.data.model.Contact
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ContactDao {

  @Query("SELECT * FROM contact ORDER BY contact.name")
  fun getAll(): Flowable<List<Contact>>

  @Query("SELECT * FROM contact WHERE contactId = :id")
  fun get(id: String): Single<Contact>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdate(vararg contacts: Contact)

  @Delete
  fun delete(contact: Contact)

}