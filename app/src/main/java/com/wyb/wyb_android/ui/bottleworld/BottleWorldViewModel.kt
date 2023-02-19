package com.wyb.wyb_android.ui.bottleworld

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wyb.wyb_android.data.model.BottleWorld
import com.wyb.wyb_android.data.model.BottleWorldChallenge
import com.wyb.wyb_android.data.model.BottleWorldUser

class BottleWorldViewModel : ViewModel() {

    private val _browseList = MutableLiveData<List<BottleWorld>>()
    val browseList: LiveData<List<BottleWorld>> = _browseList

    fun fetchChallengeList() {
        _browseList.value = listOf(
            BottleWorld(
                BottleWorldChallenge("0", 1, "텀블러 사용하기텀블러 사용하기", "2022-08-30T13:04:01.369Z", 1),
                BottleWorldUser(1, "가니"),
                true
            ),
            BottleWorld(
                BottleWorldChallenge("1", 2, "텀블러 사용하기2", "2022-08-10T13:04:01.369Z", 1),
                BottleWorldUser(1, "가니"),
                false
            ),
            BottleWorld(
                BottleWorldChallenge("4", 3, "텀블러 사용하기3", "2022-08-10T13:04:01.369Z", 1),
                BottleWorldUser(1, "가니"),
                true
            ),
            BottleWorld(
                userData = BottleWorldUser(1, "가니"),
                isFollow = true
            ),
            BottleWorld(
                userData = BottleWorldUser(1, "가니"),
                isFollow = true
            ),
        )
    }
}