package io.github.sithengineer.dialer.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "contact_number")
data class ContactNumber(
    @PrimaryKey @ColumnInfo(name = "numberId") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "userId") var userId: String,
    @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "libphonenumber_numberType") var numberType: String
)