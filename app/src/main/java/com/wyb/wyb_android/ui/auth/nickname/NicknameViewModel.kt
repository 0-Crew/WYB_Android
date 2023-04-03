package com.wyb.wyb_android.ui.auth.nickname

import android.util.Log
import androidx.lifecycle.*
import com.wyb.wyb_android.data.request.NicknameRequest
import com.wyb.wyb_android.network.ServiceBuilder
import com.wyb.wyb_android.ui.setting.SettingViewModel.Companion.MAX_NICKNAME_LENGTH
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NicknameViewModel : ViewModel() {
    val nickname = MutableLiveData("")

    val duplicatedNickname = MutableLiveData("")

    val isNickNameValid = MutableLiveData<Boolean>()

    private val _isNicknameDuplicate = MutableLiveData(false)
    val isNicknameDuplicate: LiveData<Boolean> = _isNicknameDuplicate

    val isNicknameLengthValid = MediatorLiveData<Boolean>().apply {
        addSource(nickname) { this.value = it.length >= MAX_NICKNAME_LENGTH }
    }

    val isWriting = MediatorLiveData<Boolean>().apply {
        addSource(nickname) {
            this.value = it.isNotEmpty()
        }
    }

    fun postNickname() {
        viewModelScope.launch {
            try {
                val nickname = nickname.value
                if (nickname != null) {
                    val response = ServiceBuilder.userService.postNickname(
                        NicknameRequest(nickname)
                    )
                    if (response.success) {
                        isNickNameValid.postValue(true)
                    }
                }
            } catch (e: HttpException) {
                Log.d("postNickname", e.message.toString())
                if (e.code() == 400) {
                    _isNicknameDuplicate.postValue(true)
                    duplicatedNickname.value = nickname.value
                }
            }
        }
    }
}