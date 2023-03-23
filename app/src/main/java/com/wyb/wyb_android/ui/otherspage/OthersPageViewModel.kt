package com.wyb.wyb_android.ui.otherspage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wyb.wyb_android.data.model.Discomfort

class OthersPageViewModel : ViewModel() {

    val nickname = MutableLiveData("")

    val isFollowing = MutableLiveData(false)

    val levelOfJuice = MutableLiveData(0)

    private val _isChallenge = MutableLiveData<Boolean>()
    val isChallenge: LiveData<Boolean> = _isChallenge

    private val _challengeTerm = MutableLiveData("")
    val challengeTerm: LiveData<String> = _challengeTerm

    private val _discomfortList = MutableLiveData<List<Discomfort>>()
    val discomfortList: LiveData<List<Discomfort>> = _discomfortList

    private val _challengeComfort = MutableLiveData<String?>()
    val challengeComfort: LiveData<String?> = _challengeComfort
}