package android.milestone.ui.nickname

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentNicknameBinding
import android.milestone.validation
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class NicknameFragment : BaseFragment<FragmentNicknameBinding>(R.layout.fragment_nickname) {
    override fun initViews() {
        binding.run {
            etNickname.setOnEditorActionListener { v, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        if (v.text.validation()) {
                            // TODO: 2021-08-14 유효성 검사 성공시 회원가입 api 호출 후 메인페이지로 이동
                        } else {
                            // TODO: 2021-08-14 유효성 검사 실패시 에러 처리
                        }
                        return@setOnEditorActionListener true
                    }
                }
                return@setOnEditorActionListener false
            }

        }

    }
}