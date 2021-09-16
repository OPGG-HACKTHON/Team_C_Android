package android.milestone.ui.setting

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentMypageBinding
import android.milestone.ui.main.StartDestination
import android.milestone.ui.setImage
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel: SettingViewModel by viewModels()

    override fun initViews() {
        binding.viewModel = viewModel
        viewModel.updateUserData()

        viewModel.userData.observe(viewLifecycleOwner) { user ->
            binding.tvTeam.text = user?.teamName
            binding.tvName.text = user?.nickname
            binding.ivTeamLogo.setImage(user?.teamIcon)
        }
        binding.ivSetting.setOnClickListener {
            findNavController().navigate(MyPageFragmentDirections.actionFragmentSetting())
        }
        binding.ivEditProfile.setOnClickListener {
            findNavController().navigate(MyPageFragmentDirections.actionFragmentEditNickname())
        }
        binding.ivMyPlayers.setOnClickListener {
            findNavController().navigate(MyPageFragmentDirections.actionFragmentPreference())
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
            (requireActivity() as? StartDestination)?.goToStartDestination()
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
            (requireActivity() as? StartDestination)?.goToStartDestination()
        }
    }
}