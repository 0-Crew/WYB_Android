package com.wyb.wyb_android.ui.open

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wyb.wyb_android.R
import java.util.*

class ChallengeOpenViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    private val _randomHint = MutableLiveData<String>()
    val randomHint: LiveData<String> = _randomHint

    fun getRandomHintString() {
        val hintList = context.resources.getStringArray(R.array.challenge_open_comfort_hint_list)
        val randomIndex = Random().nextInt(hintList.size - 1)
        _randomHint.value = hintList[randomIndex]
    }
}