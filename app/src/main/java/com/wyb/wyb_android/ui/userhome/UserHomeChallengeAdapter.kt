package com.wyb.wyb_android.ui.userhome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.model.Discomfort
import com.wyb.wyb_android.databinding.ItemUserHomeChallengeBinding
import com.wyb.wyb_android.util.HomeUtils.isChallengeFailed
import com.wyb.wyb_android.util.HomeUtils.isDateToday
import com.wyb.wyb_android.util.HomeUtils.setWaterDropDateText

class UserHomeChallengeAdapter :
    ListAdapter<Discomfort, UserHomeChallengeAdapter.UserHomeChallengeViewHolder>(
        BottleWorldDiffUtil()
    ) {

    inner class UserHomeChallengeViewHolder(
        private val binding: ItemUserHomeChallengeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Discomfort, position: Int) {
            binding.data = data

            val waterDropDate = setWaterDropDateText(data.createdAt, position)
            binding.cbWaterDrop.apply {
                this.text = waterDropDate

                if (isDateToday(waterDropDate)) {
                    setTextColor(
                        ResourcesCompat.getColorStateList(resources, R.color.orange, null)
                    )
                    setBackgroundResource(R.drawable.ic_water_43_orange)
                }
                if (isChallengeFailed(data.createdAt, position, data.isFinished)) {
                    setTextColor(
                        ResourcesCompat.getColorStateList(resources, R.color.gray_1, null)
                    )
                    setBackgroundResource(R.drawable.ic_water_43_orange)
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
        holder.onBind(getItem(position), position)
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