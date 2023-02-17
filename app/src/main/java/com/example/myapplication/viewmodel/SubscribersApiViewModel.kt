package com.example.myapplication.viewmodel

import androidx.lifecycle.*
import com.example.myapplication.network.NodejsApi
import com.example.myapplication.repository.SubscribersApiRepository
import com.example.myapplication.roomDatabase.Subscribers
import kotlinx.coroutines.*
import java.util.*

class SubscribersApiViewModel(private val repository: SubscribersApiRepository) : ViewModel() {


    private val _subscribers = MutableLiveData<List<Subscribers>>()

    val subscribers: LiveData<List<Subscribers>>
        get() = _subscribers
//
    private var viewmModelJob = Job()
    private var coroutineScope = CoroutineScope(viewmModelJob + Dispatchers.Main)
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

    fun fetchSubscribers() {

        coroutineScope.launch {
            val getPropertiesDeferred = NodejsApi.subscribersApi.getSubscribersAsync()
            try {
                val listResult = getPropertiesDeferred.await()
                _subscribers.value = listResult
            } catch (e: Exception) {

                _subscribers.value = ArrayList()
            }
        }
    }
}

class SubscribersApiViewModelFactory(private val repository: SubscribersApiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscribersApiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SubscribersApiViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}