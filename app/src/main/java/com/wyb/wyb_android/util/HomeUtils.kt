package com.wyb.wyb_android.util

import java.time.LocalDate

object HomeUtils : BaseUtil() {
    val isDateToday = { date: String -> date.toInt() == LocalDate.now().dayOfMonth }

    fun setWaterDropDateText(firstDate: String, position: Int): String {
        val startDate = Utils.convertIsoStringToLocalDate(firstDate)
        val currentDate = startDate?.plusDays(position.toLong())

        if (currentDate != null) {
            return currentDate.dayOfMonth.toString()
        }
        return ""
    }

    fun isChallengeFailed(startDate: String, position: Int, isFinished: Boolean): Boolean {
        val startLocalDate = Utils.convertIsoStringToLocalDate(startDate)
        if (startLocalDate != null) {
            val waterDropLocalDate = startLocalDate.plusDays(position.toLong())
            return waterDropLocalDate.isBefore(LocalDate.now()) && !isFinished
        }
        return true
    }
}