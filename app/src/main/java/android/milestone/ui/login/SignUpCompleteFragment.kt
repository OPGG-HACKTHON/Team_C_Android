package android.milestone.ui.login

import android.content.Intent
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentSignUpCompleteBinding
import android.milestone.ui.main.MainActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpCompleteFragment :
    BaseFragment<FragmentSignUpCompleteBinding>(R.layout.fragment_sign_up_complete) {
    override fun initViews() {
        val args = SignUpCompleteFragmentArgs()
        binding.tvTitle.text = getString(R.string.fragment_sign_up_complete_title, args.nickname)
        lifecycleScope.launch {
            delay(1000L)
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
    }
}