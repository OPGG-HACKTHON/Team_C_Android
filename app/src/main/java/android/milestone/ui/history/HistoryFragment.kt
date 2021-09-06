package android.milestone.ui.history

import android.milestone.R
import android.milestone.base.BaseFragment
import android.milestone.databinding.FragmentHistoryBinding
import android.milestone.ui.history.adapter.HistoryTabAdapter
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(R.layout.fragment_history) {

    private val viewModel: HistoryViewModel by activityViewModels()

    override fun initViews() {
        binding.pager.adapter = HistoryTabAdapter(this)

        TabLayoutMediator(binding.tab, binding.pager) { tab, position ->
            tab.text = getString(if (position == 0) R.string.latest_history else R.string.best_history)
        }.attach()
    }
}