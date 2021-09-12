package android.milestone.ui.home

import android.milestone.Naming.POSITION
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentPogVoteBinding
import android.milestone.ui.home.adapter.POGPlayerVoteAdapter
import android.milestone.ui.home.viewmodel.HomeViewModel
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class POGVoteFragment : BaseFragment<FragmentPogVoteBinding>(R.layout.fragment_pog_vote) {

    private val pogAdapter = POGPlayerVoteAdapter {
        viewModel.setPogVoteCount(it.gamePlayerId)
    }
    private val viewModel: HomeViewModel by activityViewModels()

    private val position by lazy {
        arguments?.run {
            getInt(POSITION)
        }
    }

    override fun initViews() {
        binding.rvPog.adapter = pogAdapter
        viewModel.pogListResponse.observe(viewLifecycleOwner, {
            pogAdapter.submitList(if (position == 0) it.data.aTeam.player else it.data.bTeam.player)
        })
    }

    companion object {

        fun instance(position: Int) = POGVoteFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION, position)
            }
        }
    }
}