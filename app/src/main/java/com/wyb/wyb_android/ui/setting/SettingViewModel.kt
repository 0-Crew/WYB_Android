package com.wyb.wyb_android.ui.setting

import android.util.Log
import androidx.lifecycle.*
import com.wyb.wyb_android.network.ServiceBuilder
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SettingViewModel : ViewModel() {
    val userExposure = MutableLiveData<Boolean>()

    val userNickname = MutableLiveData("가니가")

    val isNicknameLengthValid = MediatorLiveData<Boolean>().apply {
        addSource(userNickname) { this.value = it.length >= MAX_NICKNAME_LENGTH }
    }

    fun fetchUserExposure() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.userService.getUserExposure().data
                userExposure.postValue(response.isPrivate)
            } catch (e: HttpException) {
                Log.d("getUserExposure", e.message.toString())
            }
        }
    }

    fun updateUserExposure() {
        viewModelScope.launch {
            try {
                ServiceBuilder.userService.updateUserExposure().data
            } catch (e: HttpException) {
                Log.d("updateUserExposure", e.message.toString())
            }
        }
    }

    companion object {
        const val MAX_NICKNAME_LENGTH = 7
    }
}