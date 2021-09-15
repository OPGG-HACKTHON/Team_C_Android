package android.milestone.ui.setting

import android.content.Intent
import android.milestone.Naming
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentSettingBinding
import android.milestone.toastShort
import android.milestone.ui.login.LoginActivity
import android.milestone.util.PrefUtil
import androidx.fragment.app.viewModels
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
            toastShort("개인정보 처리방침")
        }
        binding.btnDeleteAccount.setOnClickListener {
            PrefUtil.setStringValue(Naming.ACCESS_TOKEN, "")
            requireActivity().finish()
        }
    }
}