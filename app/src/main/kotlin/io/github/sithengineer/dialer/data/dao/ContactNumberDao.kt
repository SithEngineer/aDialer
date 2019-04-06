package io.github.sithengineer.dialer.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.github.sithengineer.dialer.data.model.ContactNumber
import io.reactivex.Single

@Dao
interface ContactNumberDao {

  @Query("SELECT * FROM contact_number WHERE contact_number.contactId = :id")
  fun get(id: String): Single<List<ContactNumber>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdate(vararg contactNumber: ContactNumber)

}