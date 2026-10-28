package com.simpleart.journal.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {

    private const val PATTERN_DAY_OF_WEEK = "EEEE"
    private const val PATTERN_MONTH_YEAR = "MMMM yyyy"
    private const val PATTERN_TIMELINE_DATE = "EEE, MMM d, yyyy"
    private const val PATTERN_SHORT_DATE = "MMM d, yyyy"

    private fun getFormatter(pattern: String): DateTimeFormatter =
        DateTimeFormatter.ofPattern(pattern, Locale.getDefault())

    fun dayOfMonth(date: LocalDate): String = date.dayOfMonth.toString()

    fun dayOfWeek(date: LocalDate): String = date.format(getFormatter(PATTERN_DAY_OF_WEEK))

    fun monthAndYear(date: LocalDate): String = date.format(getFormatter(PATTERN_MONTH_YEAR))

    fun timelineDate(date: LocalDate): String = date.format(getFormatter(PATTERN_TIMELINE_DATE))

    fun timelineDate(epochDay: Long): String = toLocalDate(epochDay).format(getFormatter(PATTERN_TIMELINE_DATE))

    fun shortDate(date: LocalDate): String = date.format(getFormatter(PATTERN_SHORT_DATE))

    fun shortDate(epochDay: Long): String = toLocalDate(epochDay).format(getFormatter(PATTERN_SHORT_DATE))

    fun toLocalDate(epochDay: Long): LocalDate = LocalDate.ofEpochDay(epochDay)

    fun toEpochMillis(date: LocalDate, zoneId: ZoneId = ZoneId.systemDefault()): Long =
        date.atStartOfDay(zoneId).toInstant().toEpochMilli()

    fun fromEpochMillis(millis: Long, zoneId: ZoneId = ZoneId.systemDefault()): LocalDate =
        Instant.ofEpochMilli(millis).atZone(zoneId).toLocalDate()
}
