package android.milestone.ui.schedule

import android.milestone.R
import androidx.annotation.StringRes

enum class MatchStatus(val id: Int, @StringRes val displayTextId: Int) {
    // -1 : 경기 전, 0 : 경기 중, 1 : 경기 종료 후 30분 이전 , 2 : 경기 종료 후 30분 이후
    NOT_STARTED(-1, R.string.not_started),
    PROGRESS(0, R.string.progress),
    FINISH_EXACTLY(1, R.string.finish),
    FINISH(2, R.string.finish);

    companion object {

        fun of(id: Int) = MatchStatus.values().find { it.id == id } ?: NOT_STARTED
    }
}