package com.example.myapplication.roomDatabase;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscribers::class], version = 1)
public abstract class SubscribersDatabase : RoomDatabase() {
    abstract fun subscribersDao() : SubscribersDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SubscribersDatabase? = null

        fun getDatabase(context: Context): SubscribersDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubscribersDatabase::class.java,
                    "subscribers_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
