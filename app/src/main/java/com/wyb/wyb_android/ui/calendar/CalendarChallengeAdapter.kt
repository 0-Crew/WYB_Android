package com.wyb.wyb_android.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.data.model.Discomfort
import com.wyb.wyb_android.databinding.ItemCalendarChallengeEndBinding
import com.wyb.wyb_android.databinding.ItemCalendarChallengeInProgressBinding

class CalendarChallengeAdapter : ListAdapter<Discomfort, RecyclerView.ViewHolder>(diffCallback) {
    private var isFinishedChallenge = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_CHALLENGE_IN_PROGRESS) {
            val binding = ItemCalendarChallengeInProgressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CalendarChallengeInProgressViewHolder(binding)
        } else {
            val binding = ItemCalendarChallengeEndBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            CalendarChallengeEndViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CalendarChallengeInProgressViewHolder) {
            holder.bind(getItem(position))
        } else if (holder is CalendarChallengeEndViewHolder) {
            holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (isFinishedChallenge) VIEW_TYPE_CHALLENGE_END else VIEW_TYPE_CHALLENGE_IN_PROGRESS

    fun updateChallengeFinished(isFinished: Boolean) {
        isFinishedChallenge = isFinished
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Discomfort>() {
            override fun areItemsTheSame(oldItem: Discomfort, newItem: Discomfort): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Discomfort, newItem: Discomfort): Boolean =
                oldItem == newItem
        }

        private const val VIEW_TYPE_CHALLENGE_IN_PROGRESS = 0
        private const val VIEW_TYPE_CHALLENGE_END = 1
    }
}

class CalendarChallengeInProgressViewHolder(private val binding: ItemCalendarChallengeInProgressBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Discomfort) {
        binding.data = item
    }
}

class CalendarChallengeEndViewHolder(private val binding: ItemCalendarChallengeEndBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Discomfort) {
        binding.data = item
    }
}