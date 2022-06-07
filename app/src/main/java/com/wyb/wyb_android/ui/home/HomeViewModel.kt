package com.wyb.wyb_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wyb.wyb_android.data.model.Challenge

class HomeViewModel : ViewModel() {

    private val _challengeList = MutableLiveData<List<Challenge>>()
    val challengeList: LiveData<List<Challenge>> = _challengeList

    private val _successItems = MutableLiveData<Set<Int>>()
    val successItems: LiveData<Set<Int>> = _successItems

    private val _isEdit = MutableLiveData(false)
    val isEdit: LiveData<Boolean> = _isEdit

    val levelOfJuice = MediatorLiveData<Int>().apply {
        addSource(successItems) {
            this.value = it.size
        }
    }

    fun setIsSuccess(itemId: Int) {
        val successItems = successItems.value.orEmpty()
        val currentItems = successItems.toHashSet().apply {
            if (this.contains(itemId)) {
                this.remove(itemId)
            } else {
                this.add(itemId)
            }
        }
        _successItems.value = currentItems
    }

    fun setIsEdit(isEdit: Boolean) {
        _isEdit.value = isEdit
    }

    fun fetchChallengeList() {
        _challengeList.value = listOf(
            Challenge(1, "불편함1", false, isToday = false, isFuture = false, 1),
            Challenge(2, "불편함1", false, isToday = false, isFuture = false, 2),
            Challenge(3, "불편함1", true, isToday = false, isFuture = false, 3),
            Challenge(4, "불편함1", false, isToday = false, isFuture = false, 4),
            Challenge(5, "불편함1", false, isToday = true, isFuture = false, 5),
            Challenge(6, "불편함1", false, isToday = false, isFuture = true, 6),
            Challenge(7, "불편함1", false, isToday = false, isFuture = true, 7)
        )
    }
}