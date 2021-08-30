package android.milestone.ui.nickname

import android.content.Intent
import android.milestone.Naming.ACCESS_TOKEN
import android.milestone.Naming.REFRESH_TOKEN
import android.milestone.PrefUtil
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentNicknameBinding
import android.milestone.isValidName
import android.milestone.toastShort
import android.milestone.ui.main.MainActivity
import android.milestone.ui.login.viewmodel.LoginViewModel
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NicknameFragment : BaseFragment<FragmentNicknameBinding>(R.layout.fragment_nickname) {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun initViews() {
        initViewModels()
        binding.run {
            etNickname.setOnEditorActionListener { v, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        if (v.text.isValidName()) {
                            viewModel.postSignUp(v.text.toString())
                        } else {
                            toastShort(ERROR_MASSAGE)
                        }
                        return@setOnEditorActionListener true
                    }
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun initViewModels() {
        viewModel.run {
            lifecycleScope.launch {
                accessToken.collect { accessToken ->
                    setToken(ACCESS_TOKEN, accessToken)
                }
                refreshToken.collect { refreshToken ->
                    setToken(REFRESH_TOKEN, refreshToken)
                }
            }
            singUpResponse
                .observe(viewLifecycleOwner, { signUpResponse ->
                    if (signUpResponse.success) {
                        val intent =
                            Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        toastShort(signUpResponse.data)
                    }
                })
        }
    }

    private fun setToken(key: String, value: String) {
        PrefUtil.setStringValue(key, value)
    }

    companion object {
        private const val ERROR_MASSAGE = "닉네임 형식이 올바르지 않습니다."
    }
}