package android.milestone.ui.setting

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentEditNicknameBinding
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNicknameFragment : BaseFragment<FragmentEditNicknameBinding>(R.layout.fragment_edit_nickname) {

    private val viewModel: SettingViewModel by viewModels()

    override fun initViews() {
        binding.viewModel = viewModel
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}