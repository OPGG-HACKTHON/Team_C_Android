package android.milestone.ui.history

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentLatestHistoryBinding
import android.milestone.ui.schedule.adapter.HistoryRecyclerViewAdapter
import androidx.fragment.app.activityViewModels

class LatestHistoryFragment : BaseFragment<FragmentLatestHistoryBinding>(R.layout.fragment_latest_history) {

    private val viewModel: HistoryViewModel by activityViewModels()

    private val adapter = HistoryRecyclerViewAdapter()

    override fun initViews() {
        binding.rvContent.adapter = adapter
        binding.layoutRefresh.setOnRefreshListener {
            viewModel.loadLatestHistory()
            binding.layoutRefresh.isRefreshing = false
        }
        viewModel.latestHistory.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
