package com.wyb.wyb_android.ui.userhome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.model.ChallengeDiscomfort
import com.wyb.wyb_android.databinding.ItemUserHomeChallengeBinding

class UserHomeChallengeAdapter :
    ListAdapter<ChallengeDiscomfort, UserHomeChallengeAdapter.UserHomeChallengeViewHolder>(
        BottleWorldDiffUtil()
    ) {

    inner class UserHomeChallengeViewHolder(
        private val binding: ItemUserHomeChallengeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ChallengeDiscomfort) {
            binding.data = data

            binding.cbWaterDrop.apply {
                if (data.isToday) {
                    setTextColor(
                        ResourcesCompat.getColorStateList(resources, R.color.orange, null)
                    )
                    setBackgroundResource(R.drawable.ic_water_43_orange)
                }
                if (data.isFuture) {
                    setTextColor(
                        ResourcesCompat.getColorStateList(resources, R.color.gray_3, null)
                    )
                    setBackgroundResource(R.drawable.ic_water_43_gray)
                }
            }

            binding.tvDiscomfort.apply {
                if (data.isToday) {
                    setTextAppearance(R.style.TextAppearance_WYBComponents_Bold_14)
                    setTextColor(
                        ResourcesCompat.getColorStateList(resources, R.color.orange, null)
                    )
                }
                if (data.isFuture) {
                    setTextColor(
                        ResourcesCompat.getColorStateList(resources, R.color.gray_2, null)
                    )
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserHomeChallengeViewHolder {
        val binding: ItemUserHomeChallengeBinding =
            ItemUserHomeChallengeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return UserHomeChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHomeChallengeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    private class BottleWorldDiffUtil : DiffUtil.ItemCallback<ChallengeDiscomfort>() {
        override fun areItemsTheSame(
            oldItem: ChallengeDiscomfort,
            newItem: ChallengeDiscomfort
        ): Boolean {
            return oldItem.discomfortId == newItem.discomfortId
        }

        override fun areContentsTheSame(
            oldItem: ChallengeDiscomfort,
            newItem: ChallengeDiscomfort
        ): Boolean {
            return oldItem == newItem
        }
    }
}