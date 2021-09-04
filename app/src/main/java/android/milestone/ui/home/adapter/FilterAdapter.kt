package android.milestone.ui.home.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.base.BaseViewHolder
import android.milestone.databinding.ItemTeamBinding
import android.milestone.network.model.auth.TeamInfoModel
import android.milestone.util.PrefUtil
import android.view.ViewGroup

class FilterAdapter(
    private val onClick: (TeamInfoModel) -> Unit
) : BaseRecyclerView<ItemTeamBinding, TeamInfoModel>(R.layout.item_team, onClick) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemTeamBinding, TeamInfoModel> {
        return FilterViewHolder(parent)
    }
}

class FilterViewHolder(parent: ViewGroup) : BaseViewHolder<ItemTeamBinding, TeamInfoModel>(
    R.layout.item_team, parent
) {
    private val unSelectTeamList = PrefUtil.getStringValue(PrefUtil.UNSELECT_TEAM_LIST, "")
        .split(",")

    override fun bind(item: TeamInfoModel, onClick: (TeamInfoModel) -> Unit) {
        super.bind(item, onClick)

        var background = if (unSelectTeamList.contains(item.id.toString())) {
            R.drawable.shape_round_line_white
        } else {
            R.drawable.shape_round_line_blue_500
        }
        itemView.setOnClickListener {
            onClick(item)
            background = if (background == R.drawable.shape_round_line_white)
                R.drawable.shape_round_line_blue_500
            else
                R.drawable.shape_round_line_white
            binding.clTeam.setBackgroundResource(background)
        }
        binding.clTeam.setBackgroundResource(background)
    }
}