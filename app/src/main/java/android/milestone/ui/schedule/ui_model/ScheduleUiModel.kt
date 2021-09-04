package android.milestone.ui.schedule.ui_model

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
}