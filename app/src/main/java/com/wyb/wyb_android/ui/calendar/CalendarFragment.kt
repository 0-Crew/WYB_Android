package com.wyb.wyb_android.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.wyb.wyb_android.R
import com.wyb.wyb_android.databinding.FragmentCalendarBinding

class CalendarFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCalendarBinding
    private val viewModel: CalendarViewModel by viewModels()

    override fun getTheme(): Int = R.style.Widget_WYB_BottomSheet_Calendar_BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        initCalendarLayout()
        viewModel.setEvent()
        addDecorators()
        addRangeSelectedListener()
        addDateChangedListener()
        addDecoratorsOnDates()

        return binding.root
    }

    private fun initCalendarLayout() {
        binding.calendar.apply {
            isDynamicHeightEnabled = true
            leftArrow.setTint(resources.getColor(R.color.light_gray_1, null))
            rightArrow.setTint(resources.getColor(R.color.light_gray_1, null))
            setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.calendar_weekday)))
        }
    }

    private fun addDecorators() {
        binding.calendar.addDecorator(DateDecorator(requireContext()))
        if (viewModel.hasContainedToday.value == true) {
            binding.calendar.addDecorator(
                TodayDecorator(
                    requireContext(),
                    resources.getColor(R.color.white, null),
                    resources.getColor(R.color.white, null),
                    false
                )
            )
        } else {
            binding.calendar.addDecorator(
                TodayDecorator(
                    requireContext(),
                    resources.getColor(R.color.dark_gray_2, null),
                    resources.getColor(R.color.dark_gray_2, null),
                    true
                )
            )
        }
    }

    private fun addDateChangedListener() {
        binding.calendar.setOnDateChangedListener { widget, date, selected ->
            if (viewModel.hasContainedToday.value == true) {
                widget.addDecorator(
                    TodayDecorator(
                        requireContext(),
                        resources.getColor(R.color.white, null),
                        resources.getColor(R.color.white, null),
                        false
                    )
                )
            } else if (selected && date == CalendarDay.today()) {
                widget.addDecorator(
                    TodayDecorator(
                        requireContext(),
                        resources.getColor(R.color.dark_gray_2, null),
                        resources.getColor(R.color.dark_gray_2, null),
                        true
                    )
                )
            } else {
                widget.addDecorator(
                    TodayDecorator(
                        requireContext(),
                        resources.getColor(R.color.gray_1, null),
                        resources.getColor(R.color.white, null),
                        true
                    )
                )
            }

            if (viewModel.prevSelectedRange.isNotEmpty()) {
                setEventTextColorDecorators(viewModel.prevSelectedRange, false)
            }
            val rangePair = viewModel.getRangeContainsSelectedDate(date) ?: return@setOnDateChangedListener
            widget.selectRange(rangePair.first, rangePair.second)
        }
    }

    private fun addDecoratorsOnDates() {
        for (index in 0 until viewModel.eventDates.size) {
            when ((index + 1) % 6) {
                1 ->  {
                    setEventDecorators(viewModel.datesLeft[index], R.drawable.inset_calendar_range_yellow_left)
                    setEventDecorators(viewModel.datesCenter[index], R.drawable.inset_calendar_range_yellow_center)
                    setEventDecorators(viewModel.datesRight[index], R.drawable.inset_calendar_range_yellow_right)
                    setEventDecorators(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_yellow_independent)
                }
                2 -> {
                    setEventDecorators(viewModel.datesLeft[index], R.drawable.inset_calendar_range_green_left)
                    setEventDecorators(viewModel.datesCenter[index], R.drawable.inset_calendar_range_green_center)
                    setEventDecorators(viewModel.datesRight[index], R.drawable.inset_calendar_range_green_right)
                    setEventDecorators(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_green_independent)
                }
                3 -> {
                    setEventDecorators(viewModel.datesLeft[index], R.drawable.inset_calendar_range_red_left)
                    setEventDecorators(viewModel.datesCenter[index], R.drawable.inset_calendar_range_red_center)
                    setEventDecorators(viewModel.datesRight[index], R.drawable.inset_calendar_range_red_right)
                    setEventDecorators(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_red_independent)
                }
                4 -> {
                    setEventDecorators(viewModel.datesLeft[index], R.drawable.inset_calendar_range_blue_left)
                    setEventDecorators(viewModel.datesCenter[index], R.drawable.inset_calendar_range_blue_center)
                    setEventDecorators(viewModel.datesRight[index], R.drawable.inset_calendar_range_blue_right)
                    setEventDecorators(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_blue_independent)
                }
                5 -> {
                    setEventDecorators(viewModel.datesLeft[index], R.drawable.inset_calendar_range_purple_left)
                    setEventDecorators(viewModel.datesCenter[index], R.drawable.inset_calendar_range_purple_center)
                    setEventDecorators(viewModel.datesRight[index], R.drawable.inset_calendar_range_purple_right)
                    setEventDecorators(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_purple_independent)
                }
                else -> {
                    setEventDecorators(viewModel.datesLeft[index], R.drawable.inset_calendar_range_pink_left)
                    setEventDecorators(viewModel.datesCenter[index], R.drawable.inset_calendar_range_pink_center)
                    setEventDecorators(viewModel.datesRight[index], R.drawable.inset_calendar_range_pink_right)
                    setEventDecorators(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_pink_independent)
                }
            }
        }

        val orangeRangeIndex = viewModel.rangeContainsToday.value
        if (viewModel.hasContainedToday.value == true && orangeRangeIndex != null) {
            setEventDecorators(viewModel.datesLeft[orangeRangeIndex], R.drawable.inset_calendar_range_orange_left)
            setEventDecorators(viewModel.datesCenter[orangeRangeIndex], R.drawable.inset_calendar_range_orange_center)
            setEventDecorators(viewModel.datesRight[orangeRangeIndex], R.drawable.inset_calendar_range_orange_right)
            setEventDecorators(viewModel.datesIndependent[orangeRangeIndex], R.drawable.inset_calendar_range_orange_independent)
        }
    }

    private fun addRangeSelectedListener() {
        binding.calendar.setOnRangeSelectedListener { _, dates ->
            setEventTextColorDecorators(dates, true)
            viewModel.prevSelectedRange.apply {
                clear()
                addAll(dates)
            }
        }
    }

    private fun setEventDecorators(dates: List<CalendarDay>, drawable: Int) {
        binding.calendar.addDecorators(
            EventDecorator(
                requireContext(),
                dates.toHashSet(),
                drawable
            )
        )
        setEventTextColorDecorators(dates, false)
    }

    private fun setEventTextColorDecorators(dates: List<CalendarDay>, isSelected: Boolean) {
        binding.calendar.addDecorators(
            EventTextColorDecorator(
                requireContext(),
                dates.toHashSet(),
                isSelected
            )
        )
    }
}