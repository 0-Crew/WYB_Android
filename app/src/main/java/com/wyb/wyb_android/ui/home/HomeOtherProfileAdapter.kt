package com.wyb.wyb_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.data.model.OtherProfile
import com.wyb.wyb_android.databinding.ItemHomeOtherProfileBinding

class HomeOtherProfileAdapter :
    ListAdapter<OtherProfile, HomeOtherProfileAdapter.OtherProfileViewHolder>(FriendDiffUtil()) {

    inner class OtherProfileViewHolder(
        private val binding: ItemHomeOtherProfileBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: OtherProfile) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherProfileViewHolder {
        val binding: ItemHomeOtherProfileBinding =
            ItemHomeOtherProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return OtherProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherProfileViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class FriendDiffUtil : DiffUtil.ItemCallback<OtherProfile>() {
        override fun areItemsTheSame(oldItem: OtherProfile, newItem: OtherProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OtherProfile, newItem: OtherProfile): Boolean {
            return oldItem == newItem
        }
    }
}