package android.milestone.ui.login

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentLoginBinding
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun initViews() {
        binding.btKakaoLogin.setOnClickListener {
        }
    }
}