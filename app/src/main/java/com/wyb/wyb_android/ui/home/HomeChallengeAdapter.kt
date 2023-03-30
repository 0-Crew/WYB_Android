package com.wyb.wyb_android.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.res.ResourcesCompat.getColorStateList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.model.ChallengeDiscomfort
import com.wyb.wyb_android.databinding.ItemHomeChallengeBinding
import com.wyb.wyb_android.extension.showPopupWindow
import com.wyb.wyb_android.util.Utils
import com.wyb.wyb_android.widget.adapter.WYBPopupWindowItemAdapter
import com.wyb.wyb_android.widget.adapter.WYBPopupWindowItemAdapter.Companion.TYPE_POPUP_SMALL

class HomeChallengeAdapter(
    private val viewModel: HomeViewModel,
    private val context: Context
) : ListAdapter<ChallengeDiscomfort, HomeChallengeAdapter.ChallengeViewHolder>(ChallengeDiffUtil()) {
    private lateinit var popupWindow: PopupWindow

    inner class ChallengeViewHolder(
        private val binding: ItemHomeChallengeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(challengeData: ChallengeDiscomfort) {
            binding.data = challengeData
            initView(challengeData)
            setBtnWaterCheckedListener(challengeData)
        }

        private fun initView(data: ChallengeDiscomfort) {
            when (data.isToday) {
                true -> {
                    initPopupWindow(R.drawable.shape_orange_stroke, data)
                    binding.tvDiscomfort.setTextAppearance(R.style.TextAppearance_WYBComponents_Bold_14)
                    binding.cbWaterDrop.apply {
                        setTextColor(getColorStateList(resources, R.color.selector_waterdrop_today_text, null))
                    }
                    setTodayCheckedView(binding.cbWaterDrop.isChecked)
                }
                else -> {
                    initPopupWindow(R.drawable.shape_gray4_stroke, data)
                    initTvDiscomfortTextColor(
                        if (data.isFuture) {
                            R.color.gray_2
                        } else {
                            R.color.gray_4
                        }
                    )
                }
            }
        }

        private fun setBtnWaterCheckedListener(data: ChallengeDiscomfort) {
            binding.cbWaterDrop.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setIsSuccess(data.discomfortId)
                when (data.isToday) {
                    true -> {
                        setTodayCheckedView(isChecked)
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
                binding.cbChallengeEdit.apply {
                    backgroundTintList = getColorStateList(resources, R.color.white, null)
                }
            } else {
                initTvDiscomfortTextColor(R.color.orange)
                binding.layoutChallengeToday.setBackgroundResource(R.drawable.shape_orange_stroke)
                binding.cbChallengeEdit.apply {
                    backgroundTintList = getColorStateList(resources, R.color.gray_1, null)
                }
            }
        }

        private fun initTvDiscomfortTextColor(resourceId: Int) {
            binding.tvDiscomfort.apply {
                setTextColor(resources.getColor(resourceId, null))
            }
        }

        private fun initPopupWindow(
            drawableRes: Int,
            data: ChallengeDiscomfort,
        ) {
            binding.cbChallengeEdit.setOnCheckedChangeListener { view, isPopupOpen ->
                val resource = view.resources
                if (isPopupOpen) {
                    popupWindow = binding.layoutChallengeToday.showPopupWindow(
                        adapter = initPopupWindowAdapter(),
                        backgroundRes = drawableRes,
                        context = context,
                        margin = if (data.day > 5) {
                            resource.getInteger(R.integer.wyb_popup_window_margin_small_up)
                        } else {
                            resource.getInteger(R.integer.wyb_popup_window_margin_small_down)
                        }
                    )
                    if (data.isToday) {
                        setTodayCheckedView(false)
                    } else {
                        binding.layoutChallengeToday.setBackgroundResource(drawableRes)
                    }

                    popupWindow.setOnDismissListener {
                        view.isChecked = false
                    }
                } else {
                    if (data.isToday) {
                        setTodayCheckedView(binding.cbWaterDrop.isChecked)
                    } else {
                        binding.layoutChallengeToday.setBackgroundColor(
                            resource.getColor(android.R.color.transparent, null)
                        )
                    }
                }
            }
        }

        private fun initPopupWindowAdapter(): WYBPopupWindowItemAdapter {
            return WYBPopupWindowItemAdapter(context, TYPE_POPUP_SMALL).apply {
                popupItemClickListener = object : WYBPopupWindowItemAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        if (position == 11) {
                            binding.layoutChallengeToday.visibility = View.GONE
                            binding.etDiscomfort.visibility = View.VISIBLE
                            Utils.requestFocus(binding.etDiscomfort)
                        } else {
                            val menuList =
                                context.resources.getStringArray(R.array.challenge_discomfort_menu_list)
                            binding.tvDiscomfort.apply {
                                this.text = menuList[position]
                                Utils.clearFocus(this)
                            }
                        }
                        popupWindow.dismiss()
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

    private class ChallengeDiffUtil : DiffUtil.ItemCallback<ChallengeDiscomfort>() {
        override fun areItemsTheSame(oldItem: ChallengeDiscomfort, newItem: ChallengeDiscomfort): Boolean {
            return oldItem.discomfortId == newItem.discomfortId
        }

        override fun areContentsTheSame(oldItem: ChallengeDiscomfort, newItem: ChallengeDiscomfort): Boolean {
            return oldItem == newItem
        }
    }
}