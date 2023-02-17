package com.example.myapplication.roomDatabase;

import androidx.room.Dao;
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.roomDatabase.Subscribers
import kotlinx.coroutines.flow.Flow

@Dao
public interface SubscribersDao {

    @Query("SELECT * FROM subscribers_table")
    fun getSubscribers(): Flow<List<Subscribers>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(subscribers: Subscribers)
}
