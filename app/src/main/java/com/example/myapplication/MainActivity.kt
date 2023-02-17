package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.roomDatabase.Subscribers
import com.example.myapplication.viewmodel.SubscribersApiViewModel
import com.example.myapplication.viewmodel.SubscribersApiViewModelFactory
import com.example.myapplication.viewmodel.SubscribersRoomViewModel
import com.example.myapplication.viewmodel.SubscribersRoomViewModelFactory

class MainActivity : AppCompatActivity() {
//    private val wordViewModel: WordViewModel by viewModels {
//        WordViewModelFactory((application as WordsApplication).repository)
//    }
    private val roomViewModel: SubscribersRoomViewModel by viewModels {
        SubscribersRoomViewModelFactory((application as SubscribersApplication).subscribersRoomRepository)
    }

    private val apiViewModel: SubscribersApiViewModel by viewModels {
        SubscribersApiViewModelFactory((application as SubscribersApplication).subscribersApiRepository)
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchSubscribers()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val progressBar = findViewById<ProgressBar>(R.id.progress)


        val adapter = SubscribersAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchSubscribers()



        roomViewModel.allSubscribers.observe(this){

                subscribers -> subscribers.forEach {
            progressBar.isVisible.not()
            if (subscribers.isEmpty()){
                progressBar.visibility
            }
            adapter.submitList(subscribers)


        }
            //progressBar.

            //Log.i("First Subscribers", "${subscribers.get(1)}")
            //subscribers -> subscribers { adapter.submitList(it) }
        }

    }
    private fun insertSubscribersToRoom(body: Subscribers?){
        try {
            if (body != null) {
                roomViewModel.insert(
                    Subscribers(
                        body.name,
                        body.subscriberToChannel,
                        body.subscribeDate
                    )
                )
            }
        }catch (e: java.lang.Exception){
            e.printStackTrace()
        }
    }
    private fun fetchSubscribers(){
        apiViewModel.fetchSubscribers()
        apiViewModel.subscribers.observe(this){
                subscribers -> insertSubscribersToRoom(subscribers[subscribers.size])
        }
    }
}