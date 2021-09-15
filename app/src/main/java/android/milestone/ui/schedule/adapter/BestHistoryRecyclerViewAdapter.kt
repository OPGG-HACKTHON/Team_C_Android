package android.milestone.ui.schedule.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.databinding.ItemMyHistoryBinding
import android.milestone.network.response.history.BestHistory

class BestHistoryRecyclerViewAdapter(
    private val onClick: (BestHistory) -> Unit = {}
) : BaseRecyclerView<ItemMyHistoryBinding, BestHistory>(R.layout.item_best_history, onClick) {

}