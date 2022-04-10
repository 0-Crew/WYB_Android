package com.wyb.wyb_android.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.ItemWybPopupWindowBinding
import kotlinx.android.synthetic.main.item_wyb_popup_window.view.*

class WYBPopupWindowItemAdapter :
    RecyclerView.Adapter<WYBPopupWindowItemAdapter.WYBPopupWindowItemViewHolder>() {
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
    ): WYBPopupWindowItemViewHolder {
        val binding =
            ItemWybPopupWindowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WYBPopupWindowItemViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: WYBPopupWindowItemViewHolder, position: Int) {
        holder.bind(data[position])

        if (selectedPos == RecyclerView.NO_POSITION && position == 0) {
            selectedPos = 0
            holder.itemView.isSelected = true
            holder.itemView.tvItem.setTextAppearance(R.style.TextAppearance_WYBComponents_PopupWindow_Bold_14)
        } else {
            if (selectedPos == position) {
                holder.itemView.isSelected = true
                holder.itemView.tvItem.setTextAppearance(R.style.TextAppearance_WYBComponents_PopupWindow_Bold_14)
            } else {
                holder.itemView.isSelected = false
                holder.itemView.tvItem.setTextAppearance(R.style.TextAppearance_WYBComponents_PopupWindow_Medium_14)
            }
        }
    }

    override fun getItemCount(): Int = data.size

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
}