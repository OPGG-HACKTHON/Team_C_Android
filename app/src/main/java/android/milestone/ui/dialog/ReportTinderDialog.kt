package android.milestone.ui.dialog

import android.milestone.R
import android.milestone.base.BaseDialogFragment
import android.milestone.databinding.DialogReportTinderBinding
import android.milestone.network.request.CreateReportRequest
import android.milestone.toastShort
import android.milestone.ui.home.viewmodel.HomeViewModel
import android.milestone.util.EventObserver
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels

class ReportTinderDialog :
    BaseDialogFragment<DialogReportTinderBinding>(R.layout.dialog_report_tinder) {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun initViews() {
        initViewModels()
        binding.run {
            tvReport.setOnClickListener {
                llReportBody.isVisible = true
            }
            tvCancel.setOnClickListener {
                dismiss()
            }
            tvReportNickname.setOnClickListener {
                createReport(tvReportNickname.text.toString())
            }
            tvReportPlayerTeam.setOnClickListener {
                createReport(tvReportPlayerTeam.text.toString())
            }
            tvReportAbuse.setOnClickListener {
                createReport(tvReportAbuse.text.toString())
            }
        }
    }

    private fun initViewModels() {
        viewModel.run {
            rootResponse.observe(viewLifecycleOwner, EventObserver { rootResponse ->
                if (rootResponse.success) {
                    showReportCompleteDialog()
                    dismiss()
                }
            })
        }
    }

    private fun showReportCompleteDialog() {
        val dialog = ReportCompleteDialog.instance(
            dialogHeightRatio = 0.2f,
            dialogWidthRatio = 0.811111f
        )
        dialog.show(parentFragmentManager, "")
    }

    private fun createReport(msg: String) {
        viewModel.currentTinderId.value?.let { currentTinderId ->
            viewModel.createReport(CreateReportRequest(currentTinderId, msg))
        }
    }

    companion object {

        fun instance(
            dialogHeightRatio: Float? = null,
            dialogWidthRatio: Float? = null
        ) = ReportTinderDialog().apply {
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