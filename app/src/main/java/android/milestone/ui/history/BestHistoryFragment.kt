package android.milestone.ui.history

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentBestHistoryBinding

class BestHistoryFragment : BaseFragment<FragmentBestHistoryBinding>(R.layout.fragment_best_history) {

    override fun initViews() {
        binding.layoutRefresh.setOnRefreshListener {
            // 새로고침
            binding.layoutRefresh.isRefreshing = false
        }
    }
}
