package android.milestone.ui.history

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentHistoryBinding
import android.milestone.ui.history.adapter.HistoryTabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(R.layout.fragment_history) {

    override fun initViews() {
        binding.pager.adapter = HistoryTabAdapter(this)

        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.text = if (position == 0) "최근 메세지" else "명예의 전당"
        }.attach()
    }
}