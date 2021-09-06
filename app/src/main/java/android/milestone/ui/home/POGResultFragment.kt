package android.milestone.ui.home

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentPogResultBinding
import android.milestone.ui.dialog.POGBottomSheetDialog
import android.milestone.ui.home.viewmodel.HomeViewModel
import android.milestone.ui.match_detail.adapter.PlayerOfGameRecyclerAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class POGResultFragment : BaseFragment<FragmentPogResultBinding>(R.layout.fragment_pog_result) {

    private val pogAdapter = PlayerOfGameRecyclerAdapter()
    private val viewModel: HomeViewModel by activityViewModels()

    private val position by lazy {
        arguments?.run {
            getInt("position")
        }
    }

    override fun initViews() {
        binding.rvPog.adapter = pogAdapter
        viewModel.playerOfGameResponse.observe(viewLifecycleOwner, {
            binding.tvTotalVote.text = getString(
                R.string.total_vote,
                if (position == 0) {
                    it.data.aTeam.player.sumOf { pogPlayer ->
                        pogPlayer.count
                    }
                } else {
                    it.data.bTeam.player.sumOf { pogPlayer -> pogPlayer.count }
                })
            pogAdapter.submitList(if (position == 0) it.data.aTeam.player else it.data.bTeam.player)
        })
    }

    companion object {
        fun instance(position: Int) = POGResultFragment().apply {
            arguments = Bundle().apply {
                putInt("position", position)
            }
        }
    }
}