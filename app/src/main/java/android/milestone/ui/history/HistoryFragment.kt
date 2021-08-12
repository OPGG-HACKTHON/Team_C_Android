package android.milestone.ui.history

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentHistoryBinding
import android.milestone.ui.history.adapter.HistoryRecyclerViewAdapter
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(R.layout.fragment_history) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContent.adapter = HistoryRecyclerViewAdapter().apply {
            submitList(
                listOf(
                    "안녕 안녕 안녕안녕 안녕 안녕\n안녕 안녕 안녕\n안녕 안녕 안녕", // dummy data
                    "안녕 안녕 안녕",
                    "안녕 안녕 안녕",
                    "안녕 안녕 안녕",
                    "안녕 안녕 안녕",
                    "안녕 안녕 안녕",
                    "안녕 안녕 안녕",
                )
            )
        }
        binding.layoutRefresh.setOnRefreshListener {
            // 새로고침
            binding.layoutRefresh.isRefreshing = false
        }
    }

    override fun initViews() {
    }
}