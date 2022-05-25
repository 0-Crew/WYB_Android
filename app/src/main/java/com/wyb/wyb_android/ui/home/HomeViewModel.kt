package com.wyb.wyb_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wyb.wyb_android.data.model.Challenge

class HomeViewModel : ViewModel() {

    private val _challengeList = MutableLiveData<List<Challenge>>()
    val challengeList: LiveData<List<Challenge>> = _challengeList

    private val _isSuccess = MutableLiveData(false)
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _isEdit = MutableLiveData(false)
    val isEdit: LiveData<Boolean> = _isEdit

    private val _levelOfJuice = MutableLiveData(7)
    val levelOfJuice: LiveData<Int> = _levelOfJuice

    fun setIsSuccess(isSuccess: Boolean) {
        _isSuccess.postValue(isSuccess)
        if (isSuccess) {
            _levelOfJuice.postValue(checkNotNull(_levelOfJuice.value) - 1)
        } else {
            _levelOfJuice.postValue(checkNotNull(_levelOfJuice.value) + 1)
        }
    }

    fun setIsEdit(isEdit: Boolean) {
        _isEdit.value = isEdit
    }

    fun initChallengeList() {
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