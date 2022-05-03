package com.wyb.wyb_android.widget

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object WYBPopupWindowItemBindingAdapters {
    @BindingAdapter("selectedPos")
    @JvmStatic
    fun setSelectedPosition(recyclerView: RecyclerView, position: Int) {
        (recyclerView.adapter as WYBPopupWindowItemAdapter).selectedPos = position
    }

    @BindingAdapter("selectedPosAttrChanged")
    @JvmStatic
    fun setRecyclerViewSelectedInverseBindingListener(
        recyclerView: RecyclerView,
        listener: InverseBindingListener?
    ) {
        (recyclerView.adapter as WYBPopupWindowItemAdapter).setItemClickListener(object :
            WYBPopupWindowItemAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener?.onChange()
            }
        })
    }

    @InverseBindingAdapter(attribute = "selectedPos", event = "selectedPosAttrChanged")
    @JvmStatic
    fun getSelectedPosition(recyclerView: RecyclerView): Int {
        return (recyclerView.adapter as WYBPopupWindowItemAdapter).selectedPos
    }

    @BindingAdapter("currentScrollPos")
    @JvmStatic
    fun setCurrentScrollPosition(recyclerView: RecyclerView, position: Int) {
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(position)
    }

    @BindingAdapter("currentScrollPosAttrChanged")
    @JvmStatic
    fun setRecyclerViewScrollInverseBindingListener(
        recyclerView: RecyclerView,
        listener: InverseBindingListener?
    ) {
        val layoutManager = (recyclerView.layoutManager as LinearLayoutManager)
        var prevPos = layoutManager.findFirstVisibleItemPosition()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy == 0) return
                val newPos = layoutManager.findFirstVisibleItemPosition()
                if (prevPos != newPos) {
                    prevPos = newPos
                    listener?.onChange()
                }
            }
        })
    }

    @InverseBindingAdapter(attribute = "currentScrollPos", event = "currentScrollPosAttrChanged")
    @JvmStatic
    fun getCurrentScrollPosition(recyclerView: RecyclerView): Int {
        return (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }
}