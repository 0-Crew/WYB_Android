package com.wyb.wyb_android.ui.otherspage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.model.Discomfort
import com.wyb.wyb_android.databinding.ItemOthersPageChallengeBinding

class OthersPageChallengeAdapter :
    ListAdapter<Discomfort, OthersPageChallengeAdapter.OthersPageChallengeViewHolder>(
        BottleWorldDiffUtil()
    ) {

    inner class OthersPageChallengeViewHolder(
        private val binding: ItemOthersPageChallengeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Discomfort) {
            binding.data = data
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OthersPageChallengeViewHolder {
        val binding: ItemOthersPageChallengeBinding =
            ItemOthersPageChallengeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return OthersPageChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OthersPageChallengeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class BottleWorldDiffUtil : DiffUtil.ItemCallback<Discomfort>() {
        override fun areItemsTheSame(oldItem: Discomfort, newItem: Discomfort): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Discomfort, newItem: Discomfort): Boolean {
            return oldItem == newItem
        }
    }
}