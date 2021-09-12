package android.milestone.ui.nickname

import android.content.Context
import android.content.Intent
import android.milestone.Naming.ACCESS_TOKEN
import android.milestone.Naming.REFRESH_TOKEN
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentNicknameBinding
import android.milestone.isValidName
import android.milestone.toastShort
import android.milestone.ui.login.viewmodel.LoginViewModel
import android.milestone.ui.main.MainActivity
import android.milestone.util.PrefUtil
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NicknameFragment : BaseFragment<FragmentNicknameBinding>(R.layout.fragment_nickname) {

    private val viewModel: LoginViewModel by activityViewModels()

    override fun initViews() {
        initViewModels()
        binding.run {
            lifecycleScope.launch {
                delay(500L)
                tvSubTitle.setTextColor(resources.getColor(R.color.blue500, null))
                etNickname.apply {
                    isEnabled = true
                    setBackgroundResource(R.drawable.shape_round_gray_line_blue_500)
                    requestFocus()
                    val imm =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.toggleSoftInput(
                        InputMethodManager.SHOW_FORCED,
                        InputMethodManager.SHOW_IMPLICIT
                    )
                }
                ibClear.apply {
                    isVisible = false
                    setImageResource(R.drawable.ic_delete)
                }
            }
            ivBack.setOnClickListener {
                it.findNavController().navigate(R.id.action_login_to_team_select)
            }
            btOk.setOnClickListener {
                viewModel.postSignUp(etNickname.text.toString())
            }
            ibClear.setOnClickListener {
                etNickname.text.clear()
            }

            etNickname.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val validName = etNickname.text.isValidName()
                    tvWaring.text = validName.second
                    ibClear.isVisible = etNickname.text.isNotBlank()
                    if (validName.first) {
                        btOk.isEnabled = true
                        etNickname.setBackgroundResource(R.drawable.shape_round_gray_line_blue_500)
                        tvSubTitle.setTextColor(resources.getColor(R.color.blue500, null))
                    } else {
                        btOk.isEnabled = false
                        etNickname.setBackgroundResource(R.drawable.shape_round_gray_line_red_500)
                        tvSubTitle.setTextColor(resources.getColor(R.color.gray500, null))
                    }
                }

                override fun afterTextChanged(editable: Editable?) {
                }
            })
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
                        val action =
                            NicknameFragmentDirections.actionNicknameToComplete(binding.etNickname.text.toString())
                        this@NicknameFragment.findNavController()
                            .navigate(action)
                    } else {
                        toastShort(signUpResponse.data)
                    }
                })
        }
    }

    private fun setToken(key: String, value: String) {
        PrefUtil.setStringValue(key, value)
    }

}