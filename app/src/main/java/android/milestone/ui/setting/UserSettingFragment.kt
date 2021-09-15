package android.milestone.ui.setting

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentMypageBinding
import android.milestone.ui.setImage
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSettingFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel: SettingViewModel by viewModels()

    override fun initViews() {
        binding.viewModel = viewModel
        viewModel.updateUserData()

        viewModel.userData.observe(viewLifecycleOwner) { user ->
            binding.tvName.text = user?.user?.nickname
            binding.tvTeam.text = user?.team?.name
            binding.ivTeamLogo.setImage(user?.team?.icon)
        }
        binding.ivSetting.setOnClickListener {
            findNavController().navigate(UserSettingFragmentDirections.actionFragmentSetting())
        }
        binding.ivEditProfile.setOnClickListener {
            findNavController().navigate(UserSettingFragmentDirections.actionFragmentEditNickname())
        }
    }
}