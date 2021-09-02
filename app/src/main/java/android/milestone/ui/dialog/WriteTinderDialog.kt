package android.milestone.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.milestone.R
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

class WriteTinderDialog(private val onSendAction: (String) -> Unit) : DialogFragment() {

    private lateinit var binding: DialogWriteTinderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_write_tinder, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dpMetrics = DisplayMetrics()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = activity?.display
            display?.getRealMetrics(dpMetrics)
        } else {
            @Suppress("DEPRECATION")
            val display = activity?.windowManager?.defaultDisplay
            if (display != null) {
                @Suppress("DEPRECATION")
                display.getMetrics(dpMetrics)
            }
        }

        dialog?.window?.setLayout(
            (dpMetrics.widthPixels * WIDTH_RATIO).toInt(),
            (dpMetrics.heightPixels * HEIGHT_RATIO).toInt()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
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
        private const val HEIGHT_RATIO = 0.8f
        private const val WIDTH_RATIO = 0.9f

        fun instance(onSendAction: (String) -> Unit) = WriteTinderDialog(onSendAction)
    }
}