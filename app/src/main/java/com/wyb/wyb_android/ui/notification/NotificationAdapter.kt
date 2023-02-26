package com.wyb.wyb_android.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.data.model.Notification
import com.wyb.wyb_android.databinding.ItemNotificationBinding

class NotificationAdapter :
    ListAdapter<Notification, NotificationAdapter.NotificationViewHolder>(diffCallback) {
    private lateinit var buttonClickListener: OnItemClickListener
    private lateinit var textClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class NotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notification, position: Int) {
            binding.data = item
            binding.tvContent.setOnClickListener { textClickListener.onItemClick(position) }
            binding.btnEvent.setOnClickListener { buttonClickListener.onItemClick(position) }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Notification>() {
            override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean =
                oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setButtonClickListener(onItemClickListener: OnItemClickListener) {
        buttonClickListener = onItemClickListener
    }

    fun setTextClickListener(onItemClickListener: OnItemClickListener) {
        textClickListener = onItemClickListener
    }
}