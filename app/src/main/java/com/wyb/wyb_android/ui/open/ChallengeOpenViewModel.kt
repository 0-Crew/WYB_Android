package com.wyb.wyb_android.ui.open

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.request.OpenRequest
import com.wyb.wyb_android.network.ServiceBuilder
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*

class ChallengeOpenViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    private val _randomHint = MutableLiveData<String>()
    val randomHint: LiveData<String> = _randomHint

    val comfort = MutableLiveData("")
    val isInputEmpty = MediatorLiveData<Boolean>().apply {
        addSource(comfort) { this.value = it.isNullOrBlank() }
    }

    private val discomfortMenu =
        context.resources.getStringArray(R.array.challenge_discomfort_menu_list)
    val discomfortPos = MutableLiveData(0)
    val discomfortScrollPos = MutableLiveData(0)
    val discomfort = MediatorLiveData<String>().apply {
        addSource(discomfortPos) {
            this.value = if (it == 11) "" else discomfortMenu[it]
        }
    }

    private val _selectedToday = MutableLiveData<Boolean?>(null)
    val selectedToday: LiveData<Boolean?> = _selectedToday

    private val _validServer = MutableLiveData<Boolean>()
    val validServer: LiveData<Boolean> = _validServer

    fun getRandomHintString() {
        val hintList = context.resources.getStringArray(R.array.challenge_open_comfort_hint_list)
        val randomIndex = Random().nextInt(hintList.size - 1)
        _randomHint.value = hintList[randomIndex]
    }

    fun setStartDateCheckBox(isToday: Boolean) {
        _selectedToday.value = isToday
    }

    fun postChallengeOpen() {
        viewModelScope.launch {
            try {
                val selectedToday = selectedToday.value
                if (selectedToday != null) {
                    val response = ServiceBuilder.challengeService.postChallengeOpen(
                        OpenRequest(
                            comfort = comfort.value.orEmpty(),
                            discomfort = discomfort.value.orEmpty(),
                            selectedToday = selectedToday
                        )
                    )
                    _validServer.postValue(response.success)
                }
            } catch (e: HttpException) {
                Log.d("postChallengeOpen", e.message.toString())
            }
        }
    }

    companion object {
        const val MAX_INPUT_LENGTH = 20
    }
}