package android.milestone.ui.ranking

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentPlayerRankingBinding
import android.milestone.ui.ranking.adapter.PlayerRankingRecyclerViewAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerRankingFragment : BaseFragment<FragmentPlayerRankingBinding>(R.layout.fragment_player_ranking) {

    private val viewModel: RankingViewModel by activityViewModels()

    private val adapter: PlayerRankingRecyclerViewAdapter = PlayerRankingRecyclerViewAdapter()

    override fun initViews() {
        binding.viewModel = viewModel
        binding.rvContent.adapter = adapter

        viewModel.playerRanking.observe(viewLifecycleOwner) { players ->
            adapter.submitList(players)
            binding.tvNoData.isVisible = players.isNullOrEmpty()
        }
    }
}