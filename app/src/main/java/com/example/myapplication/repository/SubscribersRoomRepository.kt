package com.example.myapplication.repository

import androidx.annotation.WorkerThread
import com.example.myapplication.roomDatabase.Subscribers
import com.example.myapplication.roomDatabase.SubscribersDao
import kotlinx.coroutines.flow.Flow


class SubscribersRoomRepository(private val subscribersDao: SubscribersDao) {
    val allSubscribers: Flow<List<Subscribers>> = subscribersDao.getSubscribers()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(subscribers: Subscribers) {
        subscribersDao.insert(subscribers)
    }
}