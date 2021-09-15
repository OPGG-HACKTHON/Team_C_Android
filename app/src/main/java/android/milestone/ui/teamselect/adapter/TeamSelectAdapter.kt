package android.milestone.ui.teamselect.adapter

import android.milestone.App
import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.base.BaseViewHolder
import android.milestone.databinding.ItemTeamBinding
import android.milestone.network.model.auth.TeamInfoModel
import android.view.ViewGroup

class TeamSelectAdapter(
    private val onClick: (TeamInfoModel) -> Unit
) : BaseRecyclerView<ItemTeamBinding, TeamInfoModel>(R.layout.item_team, onClick) {

    private var selectPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemTeamBinding, TeamInfoModel> {
        return TeamSelectViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemTeamBinding, TeamInfoModel>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener {
            onClick(getItem(position))
            notifyItemChanged(holder.adapterPosition)
            notifyItemChanged(selectPosition)
            selectPosition = holder.adapterPosition
        }
        holder.setSingleSelectPosition(selectPosition)
    }
}

class TeamSelectViewHolder(parent: ViewGroup) : BaseViewHolder<ItemTeamBinding, TeamInfoModel>(
    R.layout.item_team, parent
) {
    override fun bind(item: TeamInfoModel, onClick: (TeamInfoModel) -> Unit) {
        super.bind(item, onClick)
        itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun setSingleSelectPosition(selectPosition: Int) {
        if (adapterPosition == selectPosition) {
            binding.clTeam.setBackgroundResource(R.drawable.shape_round_blue500_16dp)
            binding.tvTeamName.setTextColor(App.context().getColor(R.color.white))
        } else {
            binding.clTeam.setBackgroundResource(R.drawable.shape_round_line_white)
            binding.tvTeamName.setTextColor(App.context().getColor(R.color.gray400))
        }
    }
}