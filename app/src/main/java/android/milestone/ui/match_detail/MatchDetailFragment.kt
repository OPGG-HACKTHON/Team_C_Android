package android.milestone.ui.match_detail

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentMatchDetailBinding
import android.milestone.ui.match_detail.adapter.PlayerOfGameRecyclerAdapter
import android.milestone.ui.match_detail.adapter.TopTinderRecyclerAdapter
import android.milestone.ui.schedule.MatchDetailViewModel
import android.milestone.ui.schedule.ScheduleViewModel
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MatchDetailFragment : BaseFragment<FragmentMatchDetailBinding>(R.layout.fragment_match_detail) {

    private val args: MatchDetailFragmentArgs by navArgs()

    private val scheduleViewModel: ScheduleViewModel by activityViewModels()

    private val matchDetailViewModel: MatchDetailViewModel by viewModels()

    private val pogAdapter = PlayerOfGameRecyclerAdapter()

    private val topTinderAdapter = TopTinderRecyclerAdapter()

    override fun initViews() {
        binding.viewModel = matchDetailViewModel
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val selectedSchedule = scheduleViewModel.findScheduleById(args.matchId)
        binding.item = selectedSchedule

        selectedSchedule?.also {
            binding.item = it
            matchDetailViewModel.updateMatchDetail(it.schedule.id)
            binding.layoutMatchScore.tvFirstTeamScore.setTextColor(ContextCompat.getColor(binding.root.context, it.teamAScoreColor))
            binding.layoutMatchScore.tvSecondTeamScore.setTextColor(ContextCompat.getColor(binding.root.context, it.teamBScoreColor))
        }

        binding.rvTopTinder.adapter = topTinderAdapter
        binding.rvPog.adapter = pogAdapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvTopTinder)

        binding.tvTeam1Name.setOnClickListener {
            matchDetailViewModel.changeSelectedTeam(true)
            matchDetailViewModel.loadPog(selectedSchedule?.schedule?.id ?: 0)
        }
        binding.tvTeam2Name.setOnClickListener {
            matchDetailViewModel.changeSelectedTeam(false)
            matchDetailViewModel.loadPog(selectedSchedule?.schedule?.id ?: 0)
        }

        observeData()
    }

    private fun observeData() {
        matchDetailViewModel.topTinder.observe(viewLifecycleOwner) {
            binding.tvNoTinider.isVisible = it.isNullOrEmpty()
            topTinderAdapter.submitList(it)
        }
        matchDetailViewModel.selectedTeamPlayers.observe(viewLifecycleOwner) {
            pogAdapter.submitList(it)
        }
        matchDetailViewModel.pogCount.observe(viewLifecycleOwner) {
            binding.tvPogResult.text = getString(R.string.pog_result).format(it)
        }
    }
}