package android.milestone.ui.schedule.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.databinding.ItemMyHistoryBinding
import android.milestone.network.response.history.History

class HistoryRecyclerViewAdapter(
    private val onClick: (History) -> Unit = {}
) : BaseRecyclerView<ItemMyHistoryBinding, History>(R.layout.item_my_history, onClick) {

}