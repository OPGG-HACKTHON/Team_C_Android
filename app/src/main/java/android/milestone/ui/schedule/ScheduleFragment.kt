package android.milestone.ui.schedule

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentScheduleBinding
import android.milestone.ui.schedule.adapter.ScheduleTabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>(R.layout.fragment_schedule) {

    override fun initViews() {
        binding.pager.adapter = ScheduleTabAdapter(this)

        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.text = if (position == 0) "경기" else "순위"
        }.attach()
    }
}