package android.milestone.ui.schedule

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentMatchScheduleBinding
import android.milestone.ui.history.adapter.ScheduleRecyclerViewAdapter
import android.milestone.ui.main.StartDestination
import android.milestone.util.ReadableDateTime
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchScheduleFragment : BaseFragment<FragmentMatchScheduleBinding>(R.layout.fragment_match_schedule) {

    private val viewModel: ScheduleViewModel by activityViewModels()

    private val adapter: ScheduleRecyclerViewAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ScheduleRecyclerViewAdapter { scheduleUiModel ->
            val matchId = scheduleUiModel.schedule.id
            if (scheduleUiModel.schedule.status == MatchStatus.FINISH.id) {
                findNavController().navigate(ScheduleFragmentDirections.actionFragmentMatchDetail(matchId))
            } else {
                (requireActivity() as? StartDestination)?.goToStartDestination()
            }
        }
    }

    override fun initViews() {
        binding.rvContent.adapter = adapter
        binding.viewModel = viewModel

        viewModel.scheduleData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.tvNoData.isVisible = it.isNullOrEmpty()
        }
        viewModel.searchRange.observe(viewLifecycleOwner) {
            binding.tvSelectedMonth.text = ReadableDateTime(it).toyyyyMM()
        }
        binding.layoutRefresh.setOnRefreshListener {
            viewModel.updateData()
            binding.layoutRefresh.isRefreshing = false
        }
    }
}