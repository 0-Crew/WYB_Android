package com.wyb.wyb_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

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
}