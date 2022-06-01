package com.wyb.wyb_android.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat.getColorStateList
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
            setBtnWaterCheckedListener(challengeData)
        }

        private fun setBtnWaterCheckedListener(data: Challenge) {
            binding.cbWaterDrop.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setIsSuccess(data.id)
                when (data.isToday) {
                    true -> {
                        setTodayCheckedView(isChecked)
                        binding.cbChallengeEdit.apply {
                            this.backgroundTintList = if (isChecked) {
                                getColorStateList(resources,R.color.white,null)
                            } else {
                                getColorStateList(resources,R.color.gray_1,null)
                            }
                        }
                    }
                    else -> {
                        binding.cbChallengeEdit.visibility = if (isChecked) {
                            View.INVISIBLE
                        } else {
                            View.VISIBLE
                        }
                    }
                }
            }
        }

        private fun setTodayCheckedView(isSuccess: Boolean) {
            if (isSuccess) {
                initTvDiscomfortTextColor(R.color.white)
                binding.layoutChallengeToday.setBackgroundResource(R.color.orange)
            } else {
                initTvDiscomfortTextColor(R.color.orange)
                binding.layoutChallengeToday.setBackgroundResource(R.drawable.shape_orange_stroke)
            }
        }

        private fun initTvDiscomfortTextColor(resourceId: Int) {
            binding.tvDiscomfort.apply {
                setTextColor(resources.getColor(resourceId, null))
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