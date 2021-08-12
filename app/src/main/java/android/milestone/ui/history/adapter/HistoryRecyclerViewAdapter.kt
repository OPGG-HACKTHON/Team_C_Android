package android.milestone.ui.history.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.databinding.ItemMyHistoryBinding

class HistoryRecyclerViewAdapter(
    private val onClick: (String) -> Unit = {}
) : BaseRecyclerView<ItemMyHistoryBinding, String>(R.layout.item_my_history, onClick) {

}