package android.milestone.ui.login

import android.content.Intent
import android.milestone.Naming.ACCESS_TOKEN
import android.milestone.Naming.REFRESH_TOKEN
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentLoginBinding
import android.milestone.network.request.LoginRequest
import android.milestone.toastShort
import android.milestone.ui.login.viewmodel.LoginViewModel
import android.milestone.ui.main.MainActivity
import android.milestone.util.PrefUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun initViews() {
        initViewModels()
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            tryKakaoLogin(error, token)
        }

        binding.btKakaoLogin.setOnClickListener {
            UserApiClient.instance.run {
                if (isKakaoTalkLoginAvailable(requireContext())) {
                    loginWithKakaoTalk(requireContext(), callback = callback)
                } else {
                    loginWithKakaoAccount(requireContext(), callback = callback)
                }
            }
        }
    }

    private fun tryKakaoLogin(error: Throwable?, token: OAuthToken?) {
        if (error != null) {
            toastShort(error.message.toString())
        } else if (token != null) {
            UserApiClient.instance.me { user, userError ->
                if (userError != null) {
                    toastShort(userError.message.toString())
                } else {
                    user?.id?.let { id ->
                        viewModel.postLogin(LoginRequest(id))
                    }
                }
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
            loginResponse.observe(viewLifecycleOwner, {
                when (it.status) {
                    200 -> {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                    }
                    401 -> {
                        setKakaoId(id)
                        view?.findNavController()?.navigate(R.id.action_login_to_team_select)
                    }
                }
            })
        }
    }

    private fun setToken(key: String, value: String) {
        PrefUtil.setStringValue(key, value)
        Log.e(key, value)
    }
    // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
    // eyJ1c2VySWQiOjQwLCJpYXQiOjE2MzA0MTE3MTIsImV4cCI6MTYzMDQxNTMxMiwiaXNzIjoibWlsZXN0b25lIn0.
    // bAaAAwdqqiOP3DKG2Lx_UkZvrPgxPWMV2wdThWZ1bo0
}