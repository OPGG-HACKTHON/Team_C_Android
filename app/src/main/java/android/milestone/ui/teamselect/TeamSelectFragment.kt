package android.milestone.ui.teamselect

import android.content.res.ColorStateList
import android.graphics.Color
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentTeamSelectBinding
import android.milestone.ui.login.viewmodel.LoginViewModel
import android.milestone.ui.teamselect.adapter.TeamSelectAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamSelectFragment : BaseFragment<FragmentTeamSelectBinding>(R.layout.fragment_team_select) {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var teamSelectAdapter: TeamSelectAdapter

    override fun initViews() {
        teamSelectAdapter = TeamSelectAdapter {
            viewModel.setTeamId(it.id)
            binding.btSelect.isEnabled = true
            binding.btSelect.setTextColor(Color.WHITE)
            binding.btSelect.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue500))
        }
        binding.run {
            rvTeam.adapter = teamSelectAdapter
            ivBack.setOnClickListener {
                it.findNavController().navigateUp()
            }
            tvAfterSelect.setOnClickListener {
                it.findNavController().navigate(R.id.action_team_select_to_nickname)
            }
            btSelect.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_team_select_to_nickname)
            }
        }
        initViewModels()
    }

    private fun initViewModels() {
        viewModel.run {
            teamInfo.observe(viewLifecycleOwner, {
                teamSelectAdapter.submitList(it.data)
            })
        }
    }
}