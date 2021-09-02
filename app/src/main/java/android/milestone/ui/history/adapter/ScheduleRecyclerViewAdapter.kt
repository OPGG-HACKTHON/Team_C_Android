package android.milestone.ui.history.adapter

import android.milestone.R
import android.milestone.base.BaseRecyclerView
import android.milestone.base.BaseViewHolder
import android.milestone.databinding.ItemScheduleBinding
import android.milestone.network.response.schedule.Schedule
import android.milestone.ui.schedule.MatchStatus
import android.milestone.util.DateTimeTranslator
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class ScheduleRecyclerViewAdapter(
    private val onClick: (Schedule) -> Unit = {}
) : BaseRecyclerView<ItemScheduleBinding, Schedule>(R.layout.item_schedule, onClick) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemScheduleBinding, Schedule> {
        return ScheduleViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemScheduleBinding, Schedule>, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position > 0) {
            val beforeItemDate = DateTimeTranslator.from(getItem(position - 1).startTime).toyyyyMMdd()
            val currentItemDate = DateTimeTranslator.from(getItem(position).startTime).toyyyyMMdd()
            (holder as? ScheduleViewHolder)?.bindDateVisibility(beforeItemDate != currentItemDate)
        }
    }
}

class ScheduleViewHolder(parent: ViewGroup) : BaseViewHolder<ItemScheduleBinding, Schedule>(
    R.layout.item_schedule, parent
) {

    override fun bind(item: Schedule, onClick: (Schedule) -> Unit) {
        super.bind(item, onClick)
        val startTime = DateTimeTranslator.from(item.startTime)
        val matchStatus = MatchStatus.of(item.status)
        val supportAvailable = (matchStatus == MatchStatus.PROGRESS || matchStatus == MatchStatus.FINISH_EXACTLY)
        binding.tvDate.text = startTime.toMMdd()
        binding.tvStartTime.text = startTime.toHHmm()
        binding.tvStatus.text = binding.root.context.getString(matchStatus.displayTextId)
        binding.btnMore.text = binding.root.context.getString(
            if (supportAvailable) {
                R.string.support
            } else {
                R.string.mog
            }
        )

        val btnColor = ContextCompat.getColor(binding.root.context, if (supportAvailable) R.color.blue500 else R.color.gray500)
        binding.btnMore.setTextColor(btnColor)
        binding.btnMore.setBackgroundResource(if (supportAvailable) R.drawable.shape_rect_radius12_blue_stroke else R.drawable.shape_rect_radius12_stroke)
        binding.tvTeam1Score.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                if (item.aTeamScore > item.bTeamScore) R.color.blue500 else R.color.gray300
            )
        )
        binding.tvTeam2Score.setTextColor(
            ContextCompat.getColor(
                binding.root.context,
                if (item.aTeamScore < item.bTeamScore) R.color.blue500 else R.color.gray300
            )
        )
    }

    fun bindDateVisibility(isVisible: Boolean) {
        binding.tvDate.isVisible = isVisible
        binding.view.isVisible = isVisible
    }
}