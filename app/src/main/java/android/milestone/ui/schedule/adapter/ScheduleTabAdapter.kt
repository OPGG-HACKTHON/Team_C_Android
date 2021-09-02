package android.milestone.ui.schedule.adapter

import android.milestone.ui.ranking.RankingFragment
import android.milestone.ui.schedule.MatchScheduleFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScheduleTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MatchScheduleFragment()
            else -> RankingFragment()
        }
    }
}