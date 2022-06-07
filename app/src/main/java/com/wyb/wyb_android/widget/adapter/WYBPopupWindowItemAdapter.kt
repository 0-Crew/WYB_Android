package com.wyb.wyb_android.widget.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ItemWybPopupWindowBinding
import com.wyb.wyb_android.databinding.ItemWybPopupWindowSmallBinding
import kotlinx.android.synthetic.main.item_wyb_popup_window.view.*

class WYBPopupWindowItemAdapter(context: Context, private val viewType: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = context.resources.getStringArray(R.array.challenge_discomfort_menu_list)
    var selectedPos = RecyclerView.NO_POSITION
    private lateinit var itemClickListener: OnItemClickListener

    var popupItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_POPUP_DEFAULT -> {
                val binding =
                    ItemWybPopupWindowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WYBPopupWindowItemViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemWybPopupWindowSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WYBPopupWindowItemSmallViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WYBPopupWindowItemViewHolder -> {
                holder.bind(data[position])

                val tvItem = holder.itemView.tvItem
                if (selectedPos == RecyclerView.NO_POSITION && position == 0) {
                    selectedPos = 0
                    holder.itemView.isSelected = true
                    tvItem.setTextAppearance(R.style.TextAppearance_WYBComponents_Bold_14)
                } else {
                    if (selectedPos == position) {
                        holder.itemView.isSelected = true
                        tvItem.setTextAppearance(R.style.TextAppearance_WYBComponents_Bold_14)
                    } else {
                        holder.itemView.isSelected = false
                        tvItem.setTextAppearance(R.style.TextAppearance_WYBComponents_Medium_14)
                    }
                }
                tvItem.setTextColor(ContextCompat.getColor(tvItem.context, R.color.gray_4))
            }
            is WYBPopupWindowItemSmallViewHolder -> {
                holder.bind(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = viewType

    inner class WYBPopupWindowItemViewHolder(
        private val binding: ItemWybPopupWindowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvItem.text = item

            itemView.setOnClickListener {
                notifyItemChanged(selectedPos)
                selectedPos = adapterPosition
                notifyItemChanged(selectedPos)
                itemClickListener.onItemClick(selectedPos)
            }
        }
    }

    inner class WYBPopupWindowItemSmallViewHolder(
        private val binding: ItemWybPopupWindowSmallBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tvItemSmall.text = item

            itemView.setOnClickListener {
                selectedPos = adapterPosition
                notifyItemChanged(selectedPos)
                popupItemClickListener?.onItemClick(selectedPos)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    companion object {
        const val TYPE_POPUP_DEFAULT = 0
        const val TYPE_POPUP_SMALL = 1
    }
}