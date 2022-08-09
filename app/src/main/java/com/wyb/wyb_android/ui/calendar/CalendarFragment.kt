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
        addDecorators()
        addListener()

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
        binding.calendar.apply {
            addDecorator(DateDecorator(requireContext()))
            addDecorator(
                TodayDecorator(
                    requireContext(),
                    resources.getColor(R.color.gray_1, null),
                    resources.getColor(R.color.white, null),
                    false
                )
            )
        }
    }

    private fun addListener() {
        binding.calendar.setOnDateChangedListener { widget, date, selected ->
            if (selected && date == CalendarDay.today()) {
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
        }
    }
}