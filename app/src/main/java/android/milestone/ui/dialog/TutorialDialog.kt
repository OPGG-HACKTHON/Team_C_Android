package android.milestone.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.milestone.R
import android.milestone.databinding.DialogTutorialBinding
import android.milestone.util.PrefUtil
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment

class TutorialDialog : DialogFragment() {

    private lateinit var binding: DialogTutorialBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_tutorial, container, false)
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
        binding.run {
            btStart.setOnClickListener {
                dismiss()
                PrefUtil.setBooleanValue("first", false)
            }
        }
    }

    companion object {
        private const val HEIGHT_RATIO = 0.95f
        private const val WIDTH_RATIO = 1f

        fun instance() = TutorialDialog()
    }
}