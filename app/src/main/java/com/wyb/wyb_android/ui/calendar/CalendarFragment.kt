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
        addDecoratorOnDates()
        addRangeSelectedListener()
        addDateChangedListener()
        addDecoratorsOnRange()
        setSelectedToday()

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

    private fun addDecoratorOnDates() {
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
                setEventTextColorDecorator(viewModel.prevSelectedRange, false)
            }
            val rangePair = viewModel.getRangeContainsSelectedDate(date) ?: return@setOnDateChangedListener
            widget.selectRange(rangePair.first, rangePair.second)
        }
    }

    private fun addDecoratorsOnRange() {
        for (index in 0 until viewModel.eventDates.size) {
            when ((index + 1) % 6) {
                1 ->  {
                    setEventDecorator(viewModel.datesLeft[index], R.drawable.inset_calendar_range_yellow_left)
                    setEventDecorator(viewModel.datesCenter[index], R.drawable.inset_calendar_range_yellow_center)
                    setEventDecorator(viewModel.datesRight[index], R.drawable.inset_calendar_range_yellow_right)
                    setEventDecorator(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_yellow_independent)
                }
                2 -> {
                    setEventDecorator(viewModel.datesLeft[index], R.drawable.inset_calendar_range_green_left)
                    setEventDecorator(viewModel.datesCenter[index], R.drawable.inset_calendar_range_green_center)
                    setEventDecorator(viewModel.datesRight[index], R.drawable.inset_calendar_range_green_right)
                    setEventDecorator(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_green_independent)
                }
                3 -> {
                    setEventDecorator(viewModel.datesLeft[index], R.drawable.inset_calendar_range_red_left)
                    setEventDecorator(viewModel.datesCenter[index], R.drawable.inset_calendar_range_red_center)
                    setEventDecorator(viewModel.datesRight[index], R.drawable.inset_calendar_range_red_right)
                    setEventDecorator(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_red_independent)
                }
                4 -> {
                    setEventDecorator(viewModel.datesLeft[index], R.drawable.inset_calendar_range_blue_left)
                    setEventDecorator(viewModel.datesCenter[index], R.drawable.inset_calendar_range_blue_center)
                    setEventDecorator(viewModel.datesRight[index], R.drawable.inset_calendar_range_blue_right)
                    setEventDecorator(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_blue_independent)
                }
                5 -> {
                    setEventDecorator(viewModel.datesLeft[index], R.drawable.inset_calendar_range_purple_left)
                    setEventDecorator(viewModel.datesCenter[index], R.drawable.inset_calendar_range_purple_center)
                    setEventDecorator(viewModel.datesRight[index], R.drawable.inset_calendar_range_purple_right)
                    setEventDecorator(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_purple_independent)
                }
                else -> {
                    setEventDecorator(viewModel.datesLeft[index], R.drawable.inset_calendar_range_pink_left)
                    setEventDecorator(viewModel.datesCenter[index], R.drawable.inset_calendar_range_pink_center)
                    setEventDecorator(viewModel.datesRight[index], R.drawable.inset_calendar_range_pink_right)
                    setEventDecorator(viewModel.datesIndependent[index], R.drawable.inset_calendar_range_pink_independent)
                }
            }
        }

        val orangeRangeIndex = viewModel.rangeContainsToday.value
        if (viewModel.hasContainedToday.value == true && orangeRangeIndex != null) {
            setEventDecorator(viewModel.datesLeft[orangeRangeIndex], R.drawable.inset_calendar_range_orange_left)
            setEventDecorator(viewModel.datesCenter[orangeRangeIndex], R.drawable.inset_calendar_range_orange_center)
            setEventDecorator(viewModel.datesRight[orangeRangeIndex], R.drawable.inset_calendar_range_orange_right)
            setEventDecorator(viewModel.datesIndependent[orangeRangeIndex], R.drawable.inset_calendar_range_orange_independent)
        }
    }

    private fun addRangeSelectedListener() {
        binding.calendar.setOnRangeSelectedListener { _, dates ->
            setEventTextColorDecorator(dates, true)
            viewModel.prevSelectedRange.apply {
                clear()
                addAll(dates)
            }
        }
    }

    private fun setSelectedToday() {
        if (viewModel.hasContainedToday.value == true) {
            val rangePair = viewModel.getRangeContainsSelectedDate(CalendarDay.today()) ?: return
            binding.calendar.selectRange(rangePair.first, rangePair.second)
        } else {
            binding.calendar.selectedDate = CalendarDay.today()
        }
    }

    private fun setEventDecorator(dates: List<CalendarDay>, drawable: Int) {
        binding.calendar.addDecorator(
            EventDecorator(
                requireContext(),
                dates.toHashSet(),
                drawable
            )
        )
        setEventTextColorDecorator(dates, false)
    }

    private fun setEventTextColorDecorator(dates: List<CalendarDay>, isSelected: Boolean) {
        binding.calendar.addDecorator(
            EventTextColorDecorator(
                requireContext(),
                dates.toHashSet(),
                isSelected
            )
        )
    }
}