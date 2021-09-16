package android.milestone.ui.setting

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentUserPreferenceBinding
import android.milestone.ui.setting.adapter.TeamAdapter
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserPreferenceFragment : BaseFragment<FragmentUserPreferenceBinding>(R.layout.fragment_user_preference) {

    private val viewModel: SettingViewModel by viewModels()

    private val teamAdapter = TeamAdapter()

    override fun initViews() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.rvFilter.adapter = teamAdapter
        viewModel.teamList.observe(viewLifecycleOwner) {
            teamAdapter.submitList(it)
        }
    }
}