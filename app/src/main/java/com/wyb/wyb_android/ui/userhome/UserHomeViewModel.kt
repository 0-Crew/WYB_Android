package com.wyb.wyb_android.ui.userhome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wyb.wyb_android.data.model.ChallengeDiscomfort
import com.wyb.wyb_android.data.request.FollowRequest
import com.wyb.wyb_android.data.type.NotificationType
import com.wyb.wyb_android.network.ServiceBuilder
import com.wyb.wyb_android.util.HomeUtils.isDateFuture
import com.wyb.wyb_android.util.HomeUtils.isDateToday
import com.wyb.wyb_android.util.HomeUtils.setWaterDropDate
import com.wyb.wyb_android.util.Utils.setDateWithStartAt
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserHomeViewModel : ViewModel() {

    val nickname = MutableLiveData("")

    val isFollowing = MutableLiveData<Boolean>()

    val levelOfJuice = MutableLiveData(0)

    private val _isChallenge = MutableLiveData<Boolean>()
    val isChallenge: LiveData<Boolean> = _isChallenge

    private val _challengeTerm = MutableLiveData("")
    val challengeTerm: LiveData<String> = _challengeTerm

    private val _discomfortList = MutableLiveData<List<ChallengeDiscomfort>>()
    val discomfortList: LiveData<List<ChallengeDiscomfort>> = _discomfortList

    private val _challengeComfort = MutableLiveData<String>()
    val challengeComfort: LiveData<String> = _challengeComfort

    fun fetchUserHome(userId: Int) {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.challengeService.getUserHome(userId).data
                val challengeData = response.comfort
                val discomfortList = response.discomfortList
                nickname.postValue(response.userData.nickname)
                isFollowing.postValue(response.isFollowing)

                if (challengeData != null && discomfortList != null) {
                    _isChallenge.postValue(true)
                    _challengeComfort.postValue(challengeData.comfortTitle)
                    _challengeTerm.postValue(setDateWithStartAt(challengeData.startedAt))
                    _discomfortList.postValue(discomfortList.map {
                        ChallengeDiscomfort(
                            discomfortId = it.id,
                            discomfortTitle = it.name,
                            startedAt = challengeData.startedAt,
                            isFinished = it.isFinished,
                            isToday = isDateToday(challengeData.startedAt, it.day),
                            isFuture = isDateFuture(challengeData.startedAt, it.day),
                            day = it.day,
                            waterDropDate = setWaterDropDate(challengeData.startedAt, it.day),
                        )
                    })
                    levelOfJuice.postValue(discomfortList.filter { it.isFinished }.size)
                } else {
                    _isChallenge.postValue(false)
                }
            } catch (e: HttpException) {
                Log.d("fetchUserHome", e.message().toString())
            }
        }
    }

    fun postFollow(userId: Int) {
        viewModelScope.launch {
            try {
                ServiceBuilder.bottleWorldService.postFollow(
                    FollowRequest(userId)
                )
            } catch (e: HttpException) {
                Log.d("postFollow", e.message().toString())
            }
        }
    }

    fun postCheer(userId: Int) {
        viewModelScope.launch {
            try {
                ServiceBuilder.userService.postFollow(
                    type = NotificationType.CHEER.toString(),
                    receiverUserId = userId
                )
            } catch (e: HttpException) {
                Log.d("postCheer", e.message().toString())
            }
        }
    }
}