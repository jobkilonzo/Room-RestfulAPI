package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.SubscribersAdapter.SubscribersViewHolder
import com.example.myapplication.roomDatabase.Subscribers

class SubscribersAdapter : ListAdapter<Subscribers, SubscribersViewHolder>(SubscriberDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribersViewHolder {
        return SubscribersViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: SubscribersViewHolder, position: Int) {
        val current = getItem(position)
        val  name = current.name
        val subscribeToChannel = current.subscriberToChannel
        val subscribeDate = current.subscribeDate

        if (subscribeToChannel != null) {
            if (subscribeDate != null) {
                holder.bind(name, subscribeToChannel, subscribeDate)
            }
        }
    }
    class SubscribersViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        private val name: TextView = itemView.findViewById(R.id.txtName)
        private val subscribeToChannel: TextView = itemView.findViewById(R.id.txtSubscriberToChannel)
        private val subscribeDate: TextView = itemView.findViewById(R.id.txtSubscribeDate)

        fun bind(textName: String, textSubscribeToChannel: String, textSubscribeDate: String){
            name.text = textName
            subscribeToChannel.text = textSubscribeToChannel
            subscribeDate.text = textSubscribeDate
        }
        companion object{
            fun create(parent: ViewGroup) : SubscribersViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list, parent, false)
                return SubscribersViewHolder(view)
            }
        }
    }
    object SubscriberDiffCallback : DiffUtil.ItemCallback<Subscribers>() {
        override fun areItemsTheSame(oldItem: Subscribers, newItem: Subscribers): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Subscribers, newItem: Subscribers): Boolean {
            return oldItem.name == newItem.name
        }
    }

}