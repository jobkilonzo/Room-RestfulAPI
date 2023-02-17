package com.example.myapplication

import android.app.Application
import com.example.myapplication.repository.SubscribersApiRepository
import com.example.myapplication.repository.SubscribersRoomRepository
import com.example.myapplication.roomDatabase.SubscribersDatabase

class SubscribersApplication: Application() {
    private val database by lazy { SubscribersDatabase.getDatabase(this) }
    val subscribersApiRepository by lazy { SubscribersApiRepository() }
    val subscribersRoomRepository by lazy { SubscribersRoomRepository(database.subscribersDao()) }

}