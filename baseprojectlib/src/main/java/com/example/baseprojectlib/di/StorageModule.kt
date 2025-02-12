package com.example.baseprojectlib.di

import android.content.Context
import androidx.room.Room
import com.example.baseprojectlib.data.AppDatabase
import com.example.baseprojectlib.data.DatabaseInfo
import com.example.baseprojectlib.local.LocalData
import com.example.baseprojectlib.local.LocalStorage
import com.example.baseprojectlib.repository.FileHelper
import com.example.baseprojectlib.repository.FileHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun fileHelper(fileHelper: FileHelperImpl): FileHelper = fileHelper

    @Singleton
    @Provides
    fun appDatabase(
        @ApplicationContext context: Context, @DatabaseInfo dbName: String
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(localStorage: LocalData): LocalStorage = localStorage

    @Singleton
    @Provides
    fun provideClickDao(db: AppDatabase) = db.clickerDao()

    /**------------------------ Doing migrarion Database ----------------------------------------**/

//    private val migrationFrom1To2 = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            try {
//                database.execSQL("----")
//            } catch (e: Exception) {
//            }
//        }
//    }

}