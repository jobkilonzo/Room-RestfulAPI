package com.example.myapplication.repository

import com.example.myapplication.network.NodejsApi
import com.example.myapplication.roomDatabase.Subscribers
import kotlinx.coroutines.Deferred


class SubscribersApiRepository (){

//    private val _properties = MutableLiveData<List<Subscribers>>()
//
//    val properties: LiveData<List<Subscribers>>
//        get() = _properties
//
//    private var viewmModelJob = Job()
//    private var coroutineScope = CoroutineScope(viewmModelJob + Dispatchers.Main)
//
//    init {
//        getSubscribers()
//    }
//
//    private fun getSubscribers() {
//        coroutineScope.launch {
//            val getPropertiesDeferred = NodejsApi.subscribersApi.getSubscribers()
//            try {
//                val listResult =  getPropertiesDeferred.await()
//                _properties.value = listResult
//            } catch (e: Exception) {
//
//                _properties.value = ArrayList()
//            }
//        }
//    }

    suspend fun fetchSubscribers() : Deferred<List<Subscribers>> {
        return NodejsApi.subscribersApi.getSubscribersAsync()
    }



}