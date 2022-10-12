package com.wyb.wyb_android.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wyb.wyb_android.util.TripleMediatorLiveData
import java.time.LocalDate

class OnBoardingViewModel : ViewModel() {
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
}