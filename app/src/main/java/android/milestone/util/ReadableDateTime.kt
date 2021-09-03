package android.milestone.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


class ReadableDateTime constructor(private val localDateTime: LocalDateTime) {

    companion object {

        fun from(yyyyMMddHHmmss: String): ReadableDateTime {
            return ReadableDateTime(to(yyyyMMddHHmmss))
        }

        private fun to(dateTimeString: String): LocalDateTime {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME)
        }
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
        val pattern = DateTimeFormatter.ofPattern("MM월 dd일 (${getDayOfWeek()})", Locale.getDefault())
        return localDateTime.format(pattern)
    }

    fun getDayOfWeek() = localDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).first()
}