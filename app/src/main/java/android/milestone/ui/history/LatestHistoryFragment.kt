package android.milestone.ui.history

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentLatestHistoryBinding
import android.milestone.ui.schedule.adapter.HistoryRecyclerViewAdapter

class LatestHistoryFragment : BaseFragment<FragmentLatestHistoryBinding>(R.layout.fragment_latest_history) {

    override fun initViews() {
        binding.rvContent.adapter = HistoryRecyclerViewAdapter().apply {
            submitList(
                listOf(
                    "null"
                )
            )
        }
        binding.layoutRefresh.setOnRefreshListener {
            // 새로고침
            binding.layoutRefresh.isRefreshing = false
        }
    }
}
