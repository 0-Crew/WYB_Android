package com.wyb.wyb_android.ui.bottleworld

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.data.model.BottleWorld
import com.wyb.wyb_android.databinding.ItemBottleWorldBinding
import com.wyb.wyb_android.util.Utils

class BottleWorldListAdapter :
    ListAdapter<BottleWorld, BottleWorldListAdapter.BottleWorldListViewHolder>(BottleWorldDiffUtil()) {

    private lateinit var itemClickListener: OnItemClickListener

    inner class BottleWorldListViewHolder(
        private val binding: ItemBottleWorldBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BottleWorld) {
            binding.data = data

            if (data.challengeData != null) {
                val startDate = Utils.convertIsoStringToLocalDate(data.challengeData.startedAt)
                val endDate = startDate?.plusDays(6)

                if (startDate != null && endDate != null) {
                    binding.tvDate.text = Utils.setDateText(startDate, endDate)
                }
            }

            binding.root.setOnClickListener {
                itemClickListener.onItemClick(data.userData.userId)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottleWorldListViewHolder {
        val binding: ItemBottleWorldBinding =
            ItemBottleWorldBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BottleWorldListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BottleWorldListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class BottleWorldDiffUtil : DiffUtil.ItemCallback<BottleWorld>() {
        override fun areItemsTheSame(oldItem: BottleWorld, newItem: BottleWorld): Boolean {
            return oldItem.userData.userId == newItem.userData.userId
        }

        override fun areContentsTheSame(oldItem: BottleWorld, newItem: BottleWorld): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(userId: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
}