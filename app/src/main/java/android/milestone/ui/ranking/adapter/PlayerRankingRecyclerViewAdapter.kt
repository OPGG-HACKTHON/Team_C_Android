package android.milestone.ui.ranking.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.databinding.ItemPlayerRankingBinding
import android.milestone.network.response.ranking.Player

class PlayerRankingRecyclerViewAdapter(
    private val onClick: (Player) -> Unit = {}
) : BaseRecyclerView<ItemPlayerRankingBinding, Player>(R.layout.item_player_ranking, onClick) {

}