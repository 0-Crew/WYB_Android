package com.wyb.wyb_android.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.wyb.wyb_android.data.SharedPreferenceController
import com.wyb.wyb_android.data.request.AuthRequest
import com.wyb.wyb_android.data.type.AuthType
import com.wyb.wyb_android.network.ServiceBuilder
import com.wyb.wyb_android.util.TripleMediatorLiveData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.time.LocalDate

class AuthViewModel : ViewModel() {
    // onBoarding
    val challengeDates = Array<LocalDate>(7) { i ->
        LocalDate.now().minusDays(i.toLong())
    }.reversedArray()

    private val _coachMark = MutableLiveData(true)
    val coachMark: LiveData<Boolean> = _coachMark

    val waterCheckBox1 = MutableLiveData(true)
    val waterCheckBox2 = MutableLiveData(false)
    val waterCheckBox3 = MutableLiveData(false)
    val waterCheckBox4 = MutableLiveData(false)
    val waterCheckBox5 = MutableLiveData(false)
    val waterCheckBox6 = MutableLiveData(false)
    val waterCheckBox7 = MutableLiveData(false)

    private val checkedCounter1 =
        TripleMediatorLiveData(waterCheckBox1, waterCheckBox2, waterCheckBox3)
    private val checkedCounter2 =
        TripleMediatorLiveData(waterCheckBox4, waterCheckBox5, waterCheckBox6)

    val levelOfJuice = MediatorLiveData<Int>().apply {
        addSource(
            TripleMediatorLiveData(checkedCounter1, checkedCounter2, waterCheckBox7)
        ) { triple ->
            val checkedCounterFirst = countChecked(triple.first)
            val checkedCounterSecond = countChecked(triple.second)
            var waterCbSeventh = 0
            if (triple.third == true) waterCbSeventh = 1
            this.value = checkedCounterFirst + checkedCounterSecond + waterCbSeventh
        }
    }

    private fun countChecked(data: Triple<Boolean?, Boolean?, Boolean?>?): Int {
        return data?.toList()?.filter { it == true }?.size ?: 0
    }

    fun removeCoachMark() {
        _coachMark.value = false
    }

    //auth
    val authType = MutableLiveData<AuthType>()

    fun postAuth(email: String, kakaoToken: String) {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.userService.postAuth(
                    AuthRequest(
                        email = email,
                        token = kakaoToken
                    )
                )
                if (response.data != null) {
                    authType.postValue(AuthType.valueOf(response.data.type.uppercase()))
                    SharedPreferenceController.setToken(response.data.accessToken)
                }
            } catch (e: HttpException) {
                Log.d("postAuth", e.message.toString())
            }
        }
    }
}