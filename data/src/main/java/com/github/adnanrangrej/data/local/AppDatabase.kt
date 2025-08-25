package com.github.adnanrangrej.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.adnanrangrej.data.local.dao.ArticleDao
import com.github.adnanrangrej.data.local.dao.RemoteKeysDao
import com.github.adnanrangrej.data.local.entity.ArticleEntity
import com.github.adnanrangrej.data.local.entity.RemoteKey

@Database(
    entities = [
        ArticleEntity::class,
        RemoteKey::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun remoteKeyDao(): RemoteKeysDao
}