package com.wyb.wyb_android.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.model.Challenge
import com.wyb.wyb_android.databinding.ItemChallengeDateBinding

class ChallengeDateAdapter(
    val viewModel: ChallengeViewModel,
    val itemList: MutableList<Challenge>
) :
    RecyclerView.Adapter<ChallengeDateAdapter.ChallengeDateViewHolder>() {
    class ChallengeDateViewHolder(
        private val binding: ItemChallengeDateBinding,
        private val viewModel: ChallengeViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(challengeData: Challenge) {
            binding.data = challengeData
            setBtnWaterClickListener()
        }

        private fun setBtnWaterClickListener() {
            binding.imgWaterOrange.setOnClickListener {
                when (viewModel.isSuccess.value) {
                    true -> {
                        setSuccessCondition(R.drawable.ic_water_success, View.INVISIBLE)
                        viewModel.setIsSuccess(false)
                    }
                    false -> {
                        setSuccessCondition(R.drawable.ic_water_orange, View.VISIBLE)
                        viewModel.setIsSuccess(true)
                    }
                }
            }
        }

        private fun setSuccessCondition(img: Int, visibility: Int) {
            binding.imgWaterOrange.setImageResource(img)
            binding.tvDateToday.visibility = visibility
            binding.btnChallengeEdit.visibility = visibility
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChallengeDateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemChallengeDateBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_challenge_date,
            parent,
            false
        )
        return ChallengeDateViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: ChallengeDateViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}