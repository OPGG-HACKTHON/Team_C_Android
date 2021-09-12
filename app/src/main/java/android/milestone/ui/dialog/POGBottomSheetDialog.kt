package android.milestone.ui.dialog

import android.app.Dialog
import android.milestone.R
import android.milestone.databinding.FragmentPogBottomSheetBinding
import android.milestone.ui.home.adapter.POGBottomSheetTabAdapter
import android.milestone.ui.home.viewmodel.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class POGBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPogBottomSheetBinding
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pog_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
        initViews()
    }

    private fun initViewModels() {
        viewModel.run {
            getPogOfGame()
            playerOfGameResponse.observe(viewLifecycleOwner, { playerOfGameResponse ->
                TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
                    val teamsOfGameInfo = playerOfGameResponse.data
                    tab.text =
                        if (position == 0) {
                            teamsOfGameInfo.aTeam.name
                        } else {
                            teamsOfGameInfo.bTeam.name
                        }
                }.attach()
            })
        }
    }

    private fun initViews() {
        binding.run {
            pager.adapter = POGBottomSheetTabAdapter(this@POGBottomSheetDialog)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.pogBottomDialog).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            setCanceledOnTouchOutside(true)
        }
    }

    companion object {
        fun instance() = POGBottomSheetDialog()
    }
}