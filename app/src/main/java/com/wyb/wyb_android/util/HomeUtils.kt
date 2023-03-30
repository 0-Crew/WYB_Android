package com.wyb.wyb_android.util

import java.time.LocalDate

object HomeUtils : BaseUtil() {
    private fun getWaterDropLocalDate(firstDate: String, day: Int): LocalDate? {
        val position = day - 1
        val startDate = Utils.convertIsoStringToLocalDate(firstDate)
        return startDate?.plusDays(position.toLong())
    }

    fun setWaterDropDate(firstDate: String, day: Int): String {
        val waterDropDate = getWaterDropLocalDate(firstDate, day)
        return waterDropDate?.dayOfMonth.toString()
    }

    fun isDateToday(firstDate: String, day: Int): Boolean {
        val waterDropDate = getWaterDropLocalDate(firstDate, day)
        val currentDate = LocalDate.now()
        return waterDropDate?.isEqual(currentDate) == true
    }

    fun isDateFuture(firstDate: String, day: Int): Boolean {
        val waterDropDate = getWaterDropLocalDate(firstDate, day)
        val currentDate = LocalDate.now()
        return waterDropDate?.isAfter(currentDate) == true
    }
}