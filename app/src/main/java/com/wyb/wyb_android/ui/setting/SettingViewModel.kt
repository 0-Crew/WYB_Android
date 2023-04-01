package com.wyb.wyb_android.ui.setting

import android.util.Log
import androidx.lifecycle.*
import com.wyb.wyb_android.data.request.NicknameRequest
import com.wyb.wyb_android.network.ServiceBuilder
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SettingViewModel : ViewModel() {
    val userExposure = MutableLiveData<Boolean>()

    val userNickname = MutableLiveData("가니가")

    val duplicatedNickname = MutableLiveData("")

    val isNickNameValid = MutableLiveData<Boolean>()

    private val _isNicknameDuplicate = MutableLiveData(false)
    val isNicknameDuplicate: LiveData<Boolean> = _isNicknameDuplicate

    val isNicknameLengthValid = MediatorLiveData<Boolean>().apply {
        addSource(userNickname) { this.value = it.length >= MAX_NICKNAME_LENGTH }
    }

    fun updateNickname() {
        viewModelScope.launch {
            try {
                val nickname = userNickname.value
                if (nickname != null) {
                    val response = ServiceBuilder.userService.postNickname(
                        NicknameRequest(nickname)
                    )
                    if (response.success) {
                        isNickNameValid.postValue(true)
                    }
                }
            } catch (e: HttpException) {
                Log.d("updateNickname", e.message.toString())
                if (e.code() == 400) {
                    _isNicknameDuplicate.postValue(true)
                    duplicatedNickname.value = userNickname.value
                }
            }
        }
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