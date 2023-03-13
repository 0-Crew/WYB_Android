package com.wyb.wyb_android.ui.otherspage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OthersPageViewModel : ViewModel() {

    val nickname = MutableLiveData("")

    val isFollowing = MutableLiveData(false)

    val levelOfJuice = MutableLiveData(0)

    private val _isChallenge = MutableLiveData<Boolean>()
    val isChallenge: LiveData<Boolean> = _isChallenge

    private val _challengeTerm = MutableLiveData("")
    val challengeTerm: LiveData<String> = _challengeTerm

    private val _challengeComfort = MutableLiveData<String?>()
    val challengeComfort: LiveData<String?> = _challengeComfort
}