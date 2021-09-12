package android.milestone.ui.home.adapter

import android.milestone.ui.home.POGVoteFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class POGListTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = POGVoteFragment.instance(position)
}