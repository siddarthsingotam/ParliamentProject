/**
 * AppDatabase.kt
 *
 * Date: 20-Oct-2024
 * Author: Siddarth Singotam
 *
 * This file defines the Room database for the Parliament Members application.
 * It includes the Review entity and provides access to the ratingDao.
 */

package com.example.parliamentmembers.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.parliamentmembers.models.Review

@Database(entities = [Review::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun ratingDao(): ratingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "comment_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}