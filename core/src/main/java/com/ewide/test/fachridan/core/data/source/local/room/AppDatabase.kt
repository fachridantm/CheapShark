package com.ewide.test.fachridan.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewide.test.fachridan.core.data.source.local.entity.DealEntity

@Database(entities = [DealEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dealDao(): DealDao
}