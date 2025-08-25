package com.github.adnanrangrej.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.adnanrangrej.data.local.entity.RemoteKey

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKeys(keys: List<RemoteKey>)

    @Query("SELECT * FROM remote_keys WHERE articleUrl = :url")
    suspend fun getRemoteKeyByUrl(url: String): RemoteKey?

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()

}