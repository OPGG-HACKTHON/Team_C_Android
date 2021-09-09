package android.milestone.ui.home.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.databinding.ItemPogPlayerVoteBinding
import android.milestone.network.response.home.pog_list.PogListPlayer

class POGPlayerVoteAdapter(private val onClick: (PogListPlayer) -> Unit) :
    BaseRecyclerView<ItemPogPlayerVoteBinding, PogListPlayer>(
        R.layout.item_pog_player_vote,
        onClick
    ) {
}