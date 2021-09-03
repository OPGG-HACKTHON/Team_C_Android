package android.milestone.ui.match_detail

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentMatchDetailBinding
import android.milestone.ui.schedule.ScheduleViewModel
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailFragment : BaseFragment<FragmentMatchDetailBinding>(R.layout.fragment_match_detail) {

    private val scheduleViewModel: ScheduleViewModel by viewModels()

    override fun initViews() {
        binding.ivBack.setOnClickListener {

        }
    }
}