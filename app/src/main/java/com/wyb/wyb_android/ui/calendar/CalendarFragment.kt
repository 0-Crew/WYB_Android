package com.wyb.wyb_android.ui.calendar

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.wyb.wyb_android.R
import com.wyb.wyb_android.data.model.Discomfort
import com.wyb.wyb_android.databinding.FragmentCalendarBinding
import com.wyb.wyb_android.util.Utils
import kotlin.random.Random

class CalendarFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCalendarBinding
    private val viewModel: CalendarViewModel by viewModels()
    private lateinit var dateDecorator: DateDecorator
    private lateinit var challengeAdapter: CalendarChallengeAdapter

    override fun getTheme(): Int = R.style.Widget_WYB_BottomSheet_Calendar_BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).also { dialog ->
            dialog.behavior.skipCollapsed = true
            dialog.behavior.maxHeight = Utils.convertDpToPx(669)
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        dateDecorator = DateDecorator(requireContext())

        initCalendarLayout()
        viewModel.setEvent()
        initRecyclerView()
        addObservers()
        addDecoratorsOnDates()
        addMonthChangedListener()
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

    private fun initRecyclerView() {
        challengeAdapter = CalendarChallengeAdapter()
        binding.rvDiscomfort.adapter = challengeAdapter
        binding.rvDiscomfort.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun addObservers() {
        viewModel.isFinishedChallenge.observe(viewLifecycleOwner) { isFinished ->
            // TODO: 서버 연동 시 리스트가 정상적으로 업데이트 되는지 확인 후 해당 코드 삭제
            //  - 동일한 리스트를 연속해서 제출하면 ListAdapter 가 RecyclerView 를 업데이트하지 않는 문제가 있음
            //  - 현재는 더미데이터 중 하나의 id 값을 랜덤으로 변경하여 DiffUtil 이 해당 리스트를 이전과 다른 리스트로 인식하도록 처리해둠
            viewModel.discomfortItems.removeAt(viewModel.discomfortItems.lastIndex)
            viewModel.discomfortItems.add(
                Discomfort(
                    Random.nextInt(),
                    "물티슈 쓰지 않기",
                    "2022-08-29T19:13:14.582Z",
                    "2022-08-29T19:13:14.582Z",
                    false,
                    1,
                    7,
                    false,
                    1
                )
            )

            challengeAdapter.updateChallengeFinished(isFinished)
            challengeAdapter.submitList(viewModel.discomfortItems.map { it.copy() })

            if (!isFinished) {
                viewModel.updateSuccessItemSize()
            }
        }

        viewModel.hasSelectedToday.observe(viewLifecycleOwner) {
            binding.layoutChallengeList.isVisible = it != true
        }
    }

    private fun addDecoratorsOnDates() {
        binding.calendar.addDecorators(
            DisableDateDecorator(requireContext()),
            dateDecorator
        )
        dateDecorator.updateCurrentMonthDates(viewModel.getCurrentMonthDates(binding.calendar.currentDate))
        binding.calendar.invalidateDecorators()

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

    private fun addMonthChangedListener() {
        binding.calendar.setOnMonthChangedListener { _, date ->
            dateDecorator.updateCurrentMonthDates(viewModel.getCurrentMonthDates(date))
            binding.calendar.invalidateDecorators()
        }
    }

    private fun addDateChangedListener() {
        binding.calendar.setOnDateChangedListener { widget, date, selected ->
            if (viewModel.hasContainedToday.value == true) {
                viewModel.hasSelectedToday.value = false
                widget.addDecorator(
                    TodayDecorator(
                        requireContext(),
                        resources.getColor(R.color.white, null),
                        resources.getColor(R.color.white, null),
                        false
                    )
                )
            } else if (selected && date == CalendarDay.today()) {
                viewModel.hasSelectedToday.value = true
                widget.addDecorator(
                    TodayDecorator(
                        requireContext(),
                        resources.getColor(R.color.dark_gray_2, null),
                        resources.getColor(R.color.dark_gray_2, null),
                        true
                    )
                )
            } else {
                viewModel.hasSelectedToday.value = false
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
            viewModel.currentSelectedRange.apply {
                clear()
                addAll(dates)
            }
            viewModel.updateSelectedRangeContainsToday()

            setEventTextColorDecorator(dates, true)
            viewModel.prevSelectedRange.apply {
                clear()
                addAll(dates)
            }

            setDateText(dates[0], dates[6])
        }
    }

    private fun setSelectedToday() {
        if (viewModel.hasContainedToday.value == true) {
            val rangePair = viewModel.getRangeContainsSelectedDate(CalendarDay.today()) ?: return
            binding.calendar.selectRange(rangePair.first, rangePair.second)
        } else {
            binding.calendar.selectedDate = CalendarDay.today()
            viewModel.hasSelectedToday.value = true
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

        if (viewModel.rangeContainsToday.value != null) {
            binding.calendar.addDecorator(
                TodayDecorator(
                    requireContext(),
                    resources.getColor(R.color.white, null),
                    resources.getColor(R.color.white, null),
                    false
                )
            )
        }
    }

    private fun setDateText(firstDate: CalendarDay, lastDate: CalendarDay) {
        val startMonth = viewModel.formatDate(firstDate.month)
        val startDay = viewModel.formatDate(firstDate.day)
        val endMonth = viewModel.formatDate(lastDate.month)
        val endDay = viewModel.formatDate(lastDate.day)

        if (startMonth == endMonth) {
            binding.tvDate.text =
                getString(R.string.calendar_challenge_date_format, startMonth, startDay, endDay)
        } else {
            binding.tvDate.text = getString(
                R.string.calendar_challenge_date_format_long,
                startMonth,
                startDay,
                endMonth,
                endDay
            )
        }
    }
}