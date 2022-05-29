package com.wyb.wyb_android.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.model.Challenge
import com.wyb.wyb_android.databinding.ItemHomeChallengeBinding

class HomeChallengeAdapter(
    private val viewModel: HomeViewModel,
    private val context: Context
) : ListAdapter<Challenge, HomeChallengeAdapter.ChallengeViewHolder>(ChallengeDiffUtil()) {

    inner class ChallengeViewHolder(
        private val binding: ItemHomeChallengeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(challengeData: Challenge) {
            binding.data = challengeData
            setBtnWaterCheckedListener(challengeData.id)
        }

        private fun setBtnWaterCheckedListener(id: Int) {
            binding.cbWaterDrop.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        viewModel.setIsSuccess(id)
                        binding.btnChallengeEdit.visibility = View.INVISIBLE
                    }
                    else -> {
                        viewModel.setIsSuccess(id)
                        binding.btnChallengeEdit.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding: ItemHomeChallengeBinding =
            ItemHomeChallengeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class ChallengeDiffUtil : DiffUtil.ItemCallback<Challenge>() {
        override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
            return oldItem == newItem
        }
    }
}