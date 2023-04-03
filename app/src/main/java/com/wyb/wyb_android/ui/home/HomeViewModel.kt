package com.wyb.wyb_android.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.wyb.wyb_android.data.SharedPreferenceController
import com.wyb.wyb_android.data.model.ChallengeDiscomfort
import com.wyb.wyb_android.data.model.OtherProfile
import com.wyb.wyb_android.data.request.DiscomfortFinishRequest
import com.wyb.wyb_android.data.request.DiscomfortTitleRequest
import com.wyb.wyb_android.network.ServiceBuilder
import com.wyb.wyb_android.util.HomeUtils.isDateFuture
import com.wyb.wyb_android.util.HomeUtils.isDateToday
import com.wyb.wyb_android.util.HomeUtils.setWaterDropDate
import com.wyb.wyb_android.util.PairMediatorLiveData
import com.wyb.wyb_android.util.Utils
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel : ViewModel() {

    val nickname = MutableLiveData("")

    private val _followingList = MutableLiveData<List<OtherProfile>>()
    val followingList: LiveData<List<OtherProfile>> = _followingList

    private val _challengeList = MutableLiveData<List<ChallengeDiscomfort>>()
    val challengeList: LiveData<List<ChallengeDiscomfort>> = _challengeList

    private val _challengeComfort = MutableLiveData<String>()
    val challengeComfort: LiveData<String> = _challengeComfort

    private val _challengeTerm = MutableLiveData<String>()
    val challengeTerm: LiveData<String> = _challengeTerm

    private val _successItems = MutableLiveData<List<Int>>()
    val successItems: LiveData<List<Int>> = _successItems

    val levelOfJuice = MediatorLiveData<Int>().apply {
        addSource(successItems) {
            this.value = it.size
        }
    }

    private val validServer = MutableLiveData<Boolean>()

    val showEmptyView = MediatorLiveData<Boolean>().apply {
        addSource(
            PairMediatorLiveData(challengeList, validServer)
        ) { data ->
            val challengeList = data.first
            val validServer = data.second
            this.value = challengeList == null && validServer == true
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
        _successItems.value = currentItems.toList()
    }

    fun fetchHomeDate() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.challengeService.getHome().data
                val challengeData = response.myChallenge

                _followingList.postValue(response.followingList)
                if (challengeData != null) {
                    _challengeTerm.postValue(Utils.setDateWithStartAt(challengeData.startedAt))
                    _challengeComfort.postValue(challengeData.comfortTitle)
                    _successItems.postValue(
                        response.discomfortList.filter { it.isFinished }.map { it.id }
                    )
                    _challengeList.postValue(response.discomfortList.map {
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
                } else {
                    _challengeList.postValue(null)
                }
                validServer.postValue(true)
            } catch (e: HttpException) {
                validServer.postValue(false)
                Log.d("fetchHomeDate", e.message().toString())
            }
        }
    }

    fun postChallengeFinished(discomfortId: Int) {
        viewModelScope.launch {
            try {
                ServiceBuilder.challengeService.postDiscomfortFinish(
                    DiscomfortFinishRequest(discomfortId)
                )
            } catch (e: HttpException) {
                Log.d("postChallengeFinished", e.message().toString())
            }
        }
    }

    fun updateChallengeTitle(discomfortId: Int, discomfortTitle: String) {
        viewModelScope.launch {
            try {
                ServiceBuilder.challengeService.updateDiscomfortTitle(
                    DiscomfortTitleRequest(discomfortId, discomfortTitle)
                )
            } catch (e: HttpException) {
                Log.d("updateChallengeTitle", e.message().toString())
            }
        }
    }

    fun fetchUserInfo() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.userService.getUserInfo()
                nickname.postValue(response.data.userData.nickname)
                SharedPreferenceController.setNickname(response.data.userData.nickname)
            } catch (e: HttpException) {
                Log.d("fetchUserInfo", e.message().toString())
            }
        }
    }
}