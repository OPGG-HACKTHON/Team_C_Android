package android.milestone.ui.ranking

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentTeamRankingBinding
import android.milestone.ui.ranking.adapter.TeamRankingRecyclerViewAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamRankingFragment : BaseFragment<FragmentTeamRankingBinding>(R.layout.fragment_team_ranking) {

    private val viewModel: RankingViewModel by activityViewModels()

    private val adapter: TeamRankingRecyclerViewAdapter = TeamRankingRecyclerViewAdapter()

    override fun initViews() {
        binding.viewModel = viewModel
        binding.rvContent.adapter = adapter

        viewModel.teamRanking.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.tvNoData.isVisible = it.isNullOrEmpty()
        }
    }
}