package android.milestone.ui.history.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.base.BaseViewHolder
import android.milestone.databinding.ItemScheduleBinding
import android.milestone.ui.schedule.ui_model.ScheduleUiModel
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class ScheduleRecyclerViewAdapter(
    private val onClick: (ScheduleUiModel) -> Unit = {}
) : BaseRecyclerView<ItemScheduleBinding, ScheduleUiModel>(R.layout.item_schedule, onClick) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemScheduleBinding, ScheduleUiModel> {
        return ScheduleViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemScheduleBinding, ScheduleUiModel>, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position > 0) {
            val beforeItemDate = getItem(position - 1).startTime.toyyyyMMdd()
            val currentItemDate = getItem(position).startTime.toyyyyMMdd()
            (holder as? ScheduleViewHolder)?.bindDateVisibility(beforeItemDate != currentItemDate)
        }
    }
}

class ScheduleViewHolder(parent: ViewGroup) : BaseViewHolder<ItemScheduleBinding, ScheduleUiModel>(
    R.layout.item_schedule, parent
) {

    override fun bind(item: ScheduleUiModel, onClick: (ScheduleUiModel) -> Unit) {
        super.bind(item) {}
        binding.tvDate.text = item.startTime.toMMdd()
        binding.tvStartTime.text = item.startTime.toHHmm()
        binding.tvStatus.text = binding.root.context.getString(item.matchStatus.displayTextId)
        binding.btnMore.text = binding.root.context.getString(item.btnMoreTextResId)
        binding.btnMore.setOnClickListener {
            onClick(item)
        }
        binding.btnMore.setTextColor(ContextCompat.getColor(binding.root.context, item.btnMoreColorResId))
        binding.btnMore.setBackgroundResource(item.btnMoreBackgroundResId)
        binding.tvTeam1Score.setTextColor(ContextCompat.getColor(binding.root.context, item.teamAScoreColor))
        binding.tvTeam2Score.setTextColor(ContextCompat.getColor(binding.root.context, item.teamBScoreColor))
    }

    fun bindDateVisibility(isVisible: Boolean) {
        binding.tvDate.isVisible = isVisible
        binding.view.isVisible = isVisible
    }
}