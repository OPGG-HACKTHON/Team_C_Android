package android.milestone.ui.history

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentBestHistoryBinding
import android.milestone.ui.schedule.adapter.BestHistoryRecyclerViewAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels

class BestHistoryFragment : BaseFragment<FragmentBestHistoryBinding>(R.layout.fragment_best_history) {

    private val viewModel: HistoryViewModel by activityViewModels()

    private val adapter = BestHistoryRecyclerViewAdapter()

    override fun initViews() {
        binding.rvContent.adapter = adapter
        binding.layoutRefresh.setOnRefreshListener {
            viewModel.loadLatestHistory()
            binding.layoutRefresh.isRefreshing = false
        }
        viewModel.bestHistory.observe(viewLifecycleOwner) {
            binding.tvNoData.isVisible = it.isNullOrEmpty()
            adapter.submitList(it)
        }
    }
}
