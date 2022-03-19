package com.wyb.wyb_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.model.Comfort
import com.wyb.wyb_android.databinding.ItemChallengeContainerBinding

class ChallengeAdapter(
    private val viewModel: ChallengeViewModel,
    val itemList: MutableList<Comfort>
) :
    RecyclerView.Adapter<ChallengeAdapter.ChallengeViewHolder>() {

    class ChallengeViewHolder(
        private val binding: ItemChallengeContainerBinding,
        private val viewModel: ChallengeViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Comfort) {
            binding.data = item
            binding.rvChallengeDiscomfort.adapter =
                ChallengeDateAdapter(viewModel, item.innerList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemChallengeContainerBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_challenge_container,
            parent,
            false
        )
        return ChallengeViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}
