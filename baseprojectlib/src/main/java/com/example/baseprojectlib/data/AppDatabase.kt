package com.example.baseprojectlib.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseprojectlib.data.dao.ClickerDao
import com.example.baseprojectlib.data.entity.ClickerEntity

@Database(
    entities = [ClickerEntity::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clickerDao(): ClickerDao
}