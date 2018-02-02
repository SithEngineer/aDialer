package io.github.sithengineer.dialer.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "user")
data class User(
    @PrimaryKey @ColumnInfo(name = "userId") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "number") var number: String,
    @ColumnInfo(name = "thumbnail_path") var thumbnailPath: String,
    @ColumnInfo(name = "favorite") var isFavorite: Boolean = false
)