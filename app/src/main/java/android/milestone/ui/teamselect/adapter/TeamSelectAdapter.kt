package android.milestone.ui.teamselect.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.databinding.ItemTeamSelectBinding
import android.milestone.network.response.TeamInfoResponse

class TeamSelectAdapter(
    private val onClick : (TeamInfoResponse.TeamInfoModel) -> Unit
) : BaseRecyclerView<ItemTeamSelectBinding,TeamInfoResponse.TeamInfoModel>(R.layout.item_team_select,onClick)