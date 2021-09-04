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
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        scheduleViewModel.findScheduleById(args.matchId)?.also {
            binding.item = it
            matchDetailViewModel.updateMatchDetail(it.schedule.id)
            binding.layoutMatchScore.tvFirstTeamScore.setTextColor(ContextCompat.getColor(binding.root.context, it.teamAScoreColor))
            binding.layoutMatchScore.tvSecondTeamScore.setTextColor(ContextCompat.getColor(binding.root.context, it.teamBScoreColor))
        }

        binding.rvTopTinder.adapter = topTinderAdapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvTopTinder)

        matchDetailViewModel.topTinder.observe(viewLifecycleOwner) {
            topTinderAdapter.submitList(it)
        }

        matchDetailViewModel.players.observe(viewLifecycleOwner) {
            pogAdapter.submitList(it?.aTeam?.player)
        }
    }
}