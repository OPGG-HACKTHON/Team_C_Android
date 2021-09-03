package android.milestone.ui.schedule.ui_model

import android.milestone.R
import android.milestone.network.response.schedule.Schedule
import android.milestone.ui.schedule.MatchStatus
import android.milestone.util.ReadableDateTime

class ScheduleUiModel(
    val schedule: Schedule
) {
    val startTime = ReadableDateTime.from(schedule.startTime)
    val matchStatus = MatchStatus.of(schedule.status)
    val supportAvailable = (matchStatus == MatchStatus.PROGRESS || matchStatus == MatchStatus.FINISH_EXACTLY)
    val isTeamAWinner = schedule.aTeamScore > schedule.bTeamScore
    val isTeamBWinner = schedule.aTeamScore < schedule.bTeamScore
    val btnMoreTextResId = if (supportAvailable) R.string.support else R.string.mog
    val btnMoreColorResId = if (supportAvailable) R.color.blue500 else R.color.gray500
    val btnMoreBackgroundResId = if (supportAvailable) R.drawable.shape_rect_radius12_blue_stroke else R.drawable.shape_rect_radius12_stroke
    val teamAScoreColor = if (isTeamAWinner) R.color.blue500 else R.color.gray300
    val teamBScoreColor = if (isTeamBWinner) R.color.blue500 else R.color.gray300
}