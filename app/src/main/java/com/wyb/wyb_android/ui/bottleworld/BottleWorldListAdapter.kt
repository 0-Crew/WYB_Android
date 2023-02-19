package com.wyb.wyb_android.ui.bottleworld

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.data.model.BottleWorld
import com.wyb.wyb_android.databinding.ItemBottleWorldBinding

class BottleWorldListAdapter :
    ListAdapter<BottleWorld, BottleWorldListAdapter.BottleWorldListViewHolder>(BottleWorldDiffUtil()) {

    inner class BottleWorldListViewHolder(
        private val binding: ItemBottleWorldBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BottleWorld) {

        }
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
}