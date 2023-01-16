package com.wyb.wyb_android.ui.setting

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {
    val userNickname = MutableLiveData("")

    val nicknameMaxLength = MediatorLiveData<Boolean>().apply {
        addSource(userNickname) { this.value = it.length >= MAX_NICKNAME_LENGTH}
    }

    companion object {
        const val MAX_NICKNAME_LENGTH = 7
    }
}