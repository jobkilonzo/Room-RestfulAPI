package com.example.myapplication.viewmodel

import androidx.lifecycle.*
import com.example.myapplication.roomDatabase.Subscribers
import com.example.myapplication.repository.SubscribersRoomRepository
import kotlinx.coroutines.launch

class SubscribersRoomViewModel(private val repository: SubscribersRoomRepository) : ViewModel() {
    val allSubscribers: LiveData<List<Subscribers>> = repository.allSubscribers.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(subscribers: Subscribers) = viewModelScope.launch {
        repository.insert(subscribers)
    }


}
class SubscribersRoomViewModelFactory(private val repository: SubscribersRoomRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscribersRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SubscribersRoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}