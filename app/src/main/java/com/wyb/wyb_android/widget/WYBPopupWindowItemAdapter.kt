package com.wyb.wyb_android.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ItemWybPopupWindowBinding
import com.wyb.wyb_android.databinding.ItemWybPopupWindowSmallBinding
import kotlinx.android.synthetic.main.item_wyb_popup_window.view.*

class WYBPopupWindowItemAdapter(private val viewType: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedPos = RecyclerView.NO_POSITION
    private var data = listOf(
        R.string.popup_menu_plastic_dining_utensil,
        R.string.popup_menu_plastic_bag,
        R.string.popup_menu_reuse_disposable_bag,
        R.string.popup_menu_separate_collection,
        R.string.popup_menu_soap,
        R.string.popup_menu_dishcloth,
        R.string.popup_menu_handkerchief,
        R.string.popup_menu_unplug,
        R.string.popup_menu_mobile_receipt,
        R.string.popup_menu_delete_email,
        R.string.popup_menu_input
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_POPUP_DEFAULT -> {
                val binding =
                    ItemWybPopupWindowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WYBPopupWindowItemViewHolder(binding, parent.context)
            }
            else -> {
                val binding =
                    ItemWybPopupWindowSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                WYBPopupWindowItemSmallViewHolder(binding, parent.context)
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
        private val binding: ItemWybPopupWindowBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stringRes: Int) {
            binding.tvItem.text = context.getString(stringRes)

            itemView.setOnClickListener {
                notifyItemChanged(selectedPos)
                selectedPos = adapterPosition
                notifyItemChanged(selectedPos)
            }
        }
    }

    inner class WYBPopupWindowItemSmallViewHolder(
        private val binding: ItemWybPopupWindowSmallBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stringRes: Int) {
            binding.tvItemSmall.text = context.getString(stringRes)
        }
    }

    companion object {
        const val TYPE_POPUP_DEFAULT = 0
        const val TYPE_POPUP_SMALL = 1
    }
}