package android.milestone.ui.login

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentLoginBinding
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun initViews() {

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                loginErrorCode(error)
            } else if (token != null) {
                UserApiClient.instance.me { user, userError ->
                    if (userError != null) {
                        Log.e(userError.toString(), "사용자 정보 요청 실패")
                    } else {
                        val userToken = token.accessToken
                        val userId = user?.id.toString()
                        // TODO: 2021-08-13 서버 통신 및 로그인 처리 구현
                    }

                }
            }
        }
        binding.btKakaoLogin.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext(), callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
            }
        }
    }

    private fun loginErrorCode(error: Throwable) {
        when {
            error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                Toast.makeText(requireContext(), "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
            }
            error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                Toast.makeText(requireContext(), "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
            }
            error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                Toast.makeText(requireContext(), "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
            }
            error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                Toast.makeText(requireContext(), "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
            }
            error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                Toast.makeText(requireContext(), "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
            }
            error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                Toast.makeText(requireContext(), "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
            }
            error.toString() == AuthErrorCause.ServerError.toString() -> {
                Toast.makeText(requireContext(), "서버 내부 에러", Toast.LENGTH_SHORT).show()
            }
            error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                Toast.makeText(requireContext(), "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
            }
            else -> { // Unknown
                Toast.makeText(requireContext(), "$error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}