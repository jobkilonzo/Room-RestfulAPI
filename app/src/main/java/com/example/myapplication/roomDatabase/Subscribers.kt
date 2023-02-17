package com.example.myapplication.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscribers_table")
data class Subscribers(
    @PrimaryKey
    var name: String,
    var subscriberToChannel: String? = null,
    var subscribeDate: String? =null
)
