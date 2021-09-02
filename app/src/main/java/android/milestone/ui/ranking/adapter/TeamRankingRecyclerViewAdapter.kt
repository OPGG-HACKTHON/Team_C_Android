package android.milestone.ui.ranking.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.databinding.ItemTeamRankingBinding
import android.milestone.network.response.ranking.TeamRanking

class TeamRankingRecyclerViewAdapter(
    private val onClick: (TeamRanking) -> Unit = {}
) : BaseRecyclerView<ItemTeamRankingBinding, TeamRanking>(R.layout.item_team_ranking, onClick) {

}