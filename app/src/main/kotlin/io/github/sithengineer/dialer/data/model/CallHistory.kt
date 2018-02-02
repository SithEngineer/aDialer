package io.github.sithengineer.dialer.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "call_history")
data class CallHistory(
    @PrimaryKey @ColumnInfo(name = "callId") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "toUserId") var toUserId: String,
    @ColumnInfo(name = "timestamp") var timestamp: Long = System.currentTimeMillis()
)