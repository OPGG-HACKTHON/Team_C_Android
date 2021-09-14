package android.milestone.ui.dialog

import android.milestone.R
import android.milestone.base.BaseDialogFragment
import android.milestone.databinding.DialogReportCompleteBinding
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ReportCompleteDialog :
    BaseDialogFragment<DialogReportCompleteBinding>(R.layout.dialog_report_complete) {
    override fun initViews() {
        isCancelable = false
        lifecycleScope.launch {
            delay(1000L)
            dismiss()
        }
    }

    companion object {
        fun instance(
            dialogHeightRatio: Float? = null,
            dialogWidthRatio: Float? = null
        ) = ReportCompleteDialog().apply {
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