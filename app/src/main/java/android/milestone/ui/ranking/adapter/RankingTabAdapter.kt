package android.milestone.ui.ranking.adapter

import android.milestone.ui.ranking.PlayerRankingFragment
import android.milestone.ui.ranking.TeamRankingFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class RankingTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TeamRankingFragment()
            else -> PlayerRankingFragment()
        }
    }
}