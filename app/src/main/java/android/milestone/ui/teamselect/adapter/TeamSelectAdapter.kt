package android.milestone.ui.teamselect.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.databinding.ItemTeamSelectBinding
import android.milestone.network.model.auth.TeamInfoModel

class TeamSelectAdapter(
    private val onClick : (TeamInfoModel) -> Unit
) : BaseRecyclerView<ItemTeamSelectBinding, TeamInfoModel>(R.layout.item_team_select,onClick)