package android.milestone.ui.match_detail

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentMatchDetailBinding
import android.milestone.ui.schedule.ScheduleViewModel
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailFragment : BaseFragment<FragmentMatchDetailBinding>(R.layout.fragment_match_detail) {

    private val args: MatchDetailFragmentArgs by navArgs()

    private val scheduleViewModel: ScheduleViewModel by activityViewModels()

    override fun initViews() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.item = scheduleViewModel.findScheduleById(args.matchId)
    }
}