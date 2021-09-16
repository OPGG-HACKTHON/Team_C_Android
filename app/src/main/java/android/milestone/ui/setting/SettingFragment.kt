package android.milestone.ui.setting

import android.content.Intent
import android.milestone.Naming
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentSettingBinding
import android.milestone.goToWebsite
import android.milestone.ui.login.LoginActivity
import android.milestone.util.PrefUtil
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel: SettingViewModel by viewModels()

    override fun initViews() {
        binding.viewModel = viewModel

        binding.btnLogout.setOnClickListener {
            PrefUtil.setStringValue(Naming.ACCESS_TOKEN, "")
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
        binding.btnPrivacy.setOnClickListener {
            requireContext().goToWebsite(getString(R.string.policy_url))
        }
        binding.btnDeleteAccount.setOnClickListener {
            PrefUtil.setStringValue(Naming.ACCESS_TOKEN, "")
            requireActivity().finish()
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}