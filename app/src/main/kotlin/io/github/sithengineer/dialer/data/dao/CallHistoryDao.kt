package io.github.sithengineer.dialer.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.github.sithengineer.dialer.data.model.CallHistory
import io.reactivex.Single

@Dao
interface CallHistoryDao {

  @Query("SELECT * FROM call_history ORDER BY call_history.timestamp")
  fun getAll(): Single<List<CallHistory>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(vararg calls: CallHistory)

  @Query("DELETE FROM call_history")
  fun deleteAll()
}