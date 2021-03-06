package android.milestone.ui.nickname

import android.content.Context
import android.content.res.ColorStateList
import android.milestone.Naming.ACCESS_TOKEN
import android.milestone.Naming.REFRESH_TOKEN
import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentNicknameBinding
import android.milestone.isValidName
import android.milestone.toastShort
import android.milestone.ui.login.viewmodel.LoginViewModel
import android.milestone.util.PrefUtil
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
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
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        initViewModels()
        binding.run {
            lifecycleScope.launch {
                delay(500L)
                tvSubTitle.setTextColor(resources.getColor(R.color.blue500, null))
                etNickname.apply {
                    isEnabled = true
                    setBackgroundResource(R.drawable.shape_round_gray_line_blue_500)
                    requestFocus()
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
                it.findNavController().navigate(R.id.action_nickname_to_team_select)
            }
            btOk.setOnClickListener {
                viewModel.postSignUp(etNickname.text.toString())
                imm.hideSoftInputFromWindow(binding.etNickname.windowToken,0)
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
                    val validName = etNickname.text.toString().isValidName()
                    tvWaring.text = validName.second
                    ibClear.isVisible = etNickname.text.isNotBlank()
                    if (validName.first) {
                        btOk.isEnabled = true
                        btOk.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue500))
                        etNickname.setBackgroundResource(R.drawable.shape_round_gray_line_blue_500)
                        tvSubTitle.setTextColor(resources.getColor(R.color.blue500, null))
                    } else {
                        btOk.isEnabled = false
                        btOk.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray200))
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
                        val action = NicknameFragmentDirections.actionNicknameToComplete(binding.etNickname.text.toString())
                        this@NicknameFragment.findNavController().navigate(action)
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