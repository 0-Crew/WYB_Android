package com.wyb.wyb_android.ui.bottleworld

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wyb.wyb_android.data.model.BottleWorld
import com.wyb.wyb_android.data.model.BottleWorldChallenge
import com.wyb.wyb_android.data.model.BottleWorldUser

class BottleWorldViewModel : ViewModel() {

    private val _countFollower = MutableLiveData(2)
    val countFollower: LiveData<Int> = _countFollower

    private val _countFollowing = MutableLiveData(0)
    val countFollowing: LiveData<Int> = _countFollowing

    private val _browseList = MutableLiveData<List<BottleWorld>>()
    val browseList: LiveData<List<BottleWorld>> = _browseList

    private val _followerList = MutableLiveData<List<BottleWorld>?>()
    val followerList: LiveData<List<BottleWorld>?> = _followerList

    private val _followingList = MutableLiveData<List<BottleWorld>?>()
    val followingList: LiveData<List<BottleWorld>?> = _followingList

    fun fetchChallengeList() {
        _followingList.value = listOf()
        _followerList.value = listOf()
        _browseList.value = listOf(
            BottleWorld(
                BottleWorldChallenge("0", 1, "텀블러 사용하기텀블러 사용하기", "2022-08-30T13:04:01.369Z", 1),
                BottleWorldUser(1, "가니1"),
                true
            ),
            BottleWorld(
                BottleWorldChallenge("1", 2, "텀블러 사용하기2", "2022-08-10T13:04:01.369Z", 1),
                BottleWorldUser(2, "가니2"),
                false
            ),
            BottleWorld(
                BottleWorldChallenge("4", 3, "텀블러 사용하기3", "2022-08-10T13:04:01.369Z", 1),
                BottleWorldUser(3, "가니3"),
                true
            ),
            BottleWorld(
                BottleWorldChallenge("4", 4, "텀블러 사용하기4", "2022-08-10T13:04:01.369Z", 1),
                BottleWorldUser(4, "가니4"),
                false
            ),
            BottleWorld(
                BottleWorldChallenge("4", 4, "텀블러 사용하기5", "2022-08-10T13:04:01.369Z", 1),
                BottleWorldUser(5, "가니5"),
                true
            ),
            BottleWorld(
                userData = BottleWorldUser(4, "가니4"),
                isFollow = true
            ),
            BottleWorld(
                userData = BottleWorldUser(5, "가니5"),
                isFollow = true
            ),
        )
    }
}