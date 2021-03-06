package android.milestone.ui.home.adapter

import android.milestone.ui.home.POGResultFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class POGBottomSheetTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = POGResultFragment.instance(position)
}