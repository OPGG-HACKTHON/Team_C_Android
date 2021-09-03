package android.milestone.ui.history.adapter

import android.milestone.ui.history.BestHistoryFragment
import android.milestone.ui.history.LatestHistoryFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HistoryTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LatestHistoryFragment()
            else -> BestHistoryFragment()
        }
    }
}