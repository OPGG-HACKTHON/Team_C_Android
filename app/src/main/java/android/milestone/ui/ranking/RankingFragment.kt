package android.milestone.ui.ranking

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentRankingBinding
import android.milestone.ui.ranking.adapter.RankingTabAdapter
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingFragment : BaseFragment<FragmentRankingBinding>(R.layout.fragment_ranking) {

    private val viewModel: RankingViewModel by activityViewModels()

    override fun initViews() {
        binding.pager.adapter = RankingTabAdapter(this)

        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.text = getString(if (position == 0) R.string.ranking else R.string.mvp)
        }.attach()

        binding.layoutRefresh.setOnRefreshListener {
            viewModel.updateData()
            binding.layoutRefresh.isRefreshing = false
        }
    }
}