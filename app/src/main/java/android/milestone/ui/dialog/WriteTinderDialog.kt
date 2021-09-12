package android.milestone.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.milestone.R
import android.milestone.base.BaseDialogFragment
import android.milestone.databinding.DialogWriteTinderBinding
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment

class WriteTinderDialog(private val onSendAction: (String) -> Unit) :
    BaseDialogFragment<DialogWriteTinderBinding>(R.layout.dialog_write_tinder) {

    override fun initViews() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        binding.run {
            ivSend.isEnabled = false
            etTinder.addTextChangedListener { editable ->
                editable?.let {
                    ivSend.run {
                        if (it.isNotEmpty()) {
                            isEnabled = true
                            setImageResource(R.drawable.ic_send_btn_active)
                        } else {
                            isEnabled = false
                            setImageResource(R.drawable.ic_send_btn)
                        }
                    }
                }
            }
            ivSend.setOnClickListener {
                imm.hideSoftInputFromWindow(etTinder.windowToken, 0)
                onSendAction(etTinder.text.toString())
                dismiss()
            }
        }
    }

    companion object {

        fun instance(
            onSendAction: (String) -> Unit,
            dialogHeightRatio: Float? = null,
            dialogWidthRatio: Float? = null
        ) = WriteTinderDialog(onSendAction).apply {
            arguments = Bundle().apply {
                if (dialogHeightRatio != null) {
                    putFloat(DIALOG_HEIGHT_RATIO, dialogHeightRatio)
                }
                if (dialogWidthRatio != null) {
                    putFloat(DIALOG_WIDTH_RATIO, dialogWidthRatio)
                }
            }
        }
    }
}