package android.milestone.ui.dialog

import android.milestone.R
import android.milestone.base.BaseDialogFragment
import android.milestone.databinding.DialogTutorialBinding
import android.milestone.util.PrefUtil
import android.os.Bundle
import android.view.View

class TutorialDialog : BaseDialogFragment<DialogTutorialBinding>(R.layout.dialog_tutorial) {

    override fun initViews() {
        binding.run {
            btStart.setOnClickListener {
                dismiss()
                PrefUtil.setBooleanValue("first", false)
            }
        }
    }

    companion object {
        fun instance(
            dialogHeightRatio: Float? = null,
            dialogWidthRatio: Float? = null
        ) = TutorialDialog().apply {
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