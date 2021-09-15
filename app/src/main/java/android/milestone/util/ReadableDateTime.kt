package android.milestone.util

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


class ReadableDateTime constructor(private val localDateTime: LocalDateTime) {

    private object TimeMaximum {
        const val MIN = 60
        const val HOUR = 24
        const val DAY = 30
        const val MONTH = 12
    }

    companion object {

        fun from(yyyyMMddHHmmss: String): ReadableDateTime {
            return ReadableDateTime(to(yyyyMMddHHmmss))
        }

        private fun to(dateTimeString: String): LocalDateTime {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME).plusHours(9)
        }
    }

    fun toMinuteDifference(): String {
        return formatTimeString(Duration.between(localDateTime, LocalDateTime.now()).toMinutes())
    }

    private fun formatTimeString(diffMinute: Long): String {
        var diffTime = diffMinute
        var msg: String? = null
        when {
            diffTime < TimeMaximum.MIN -> {
                msg = diffTime.toString() + "분 전"
            }
            TimeMaximum.MIN.let { diffTime /= it; diffTime } < TimeMaximum.HOUR -> {
                msg = diffTime.toString() + "시간 전"
            }
            TimeMaximum.HOUR.let { diffTime /= it; diffTime } < TimeMaximum.DAY -> {
                msg = diffTime.toString() + "일 전"
            }
            TimeMaximum.DAY.let { diffTime /= it; diffTime } < TimeMaximum.MONTH -> {
                msg = diffTime.toString() + "달 전"
            }
            else -> {
                msg = diffTime.toString() + "년 전"
            }
        }
        return msg ?: ""
    }

    fun toHHmm(): String {
        val pattern = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
        return localDateTime.format(pattern)
    }

    fun toyyyyMMdd(): String {
        val pattern = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.getDefault())
        return localDateTime.format(pattern)
    }

    fun toyyyyMM(): String {
        val pattern = DateTimeFormatter.ofPattern("yyyy년 MM월", Locale.getDefault())
        return localDateTime.format(pattern)
    }

    fun toMMdd(): String {
        val pattern =
            DateTimeFormatter.ofPattern("MM월 dd일 (${getDayOfWeek()})", Locale.getDefault())
        return localDateTime.format(pattern)
    }

    fun getDayOfWeek() =
        localDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).first()
}