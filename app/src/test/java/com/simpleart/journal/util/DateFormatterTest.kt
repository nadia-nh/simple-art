package com.simpleart.journal.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class DateFormatterTest {

    private val testDate = LocalDate.of(2026, 10, 28) // Wednesday

    @Test
    fun dayOfMonth_formatsCorrectly() {
        assertEquals("28", DateFormatter.dayOfMonth(testDate))
    }

    @Test
    fun dayOfWeek_formatsCorrectly() {
        assertEquals("Wednesday", DateFormatter.dayOfWeek(testDate))
    }

    @Test
    fun monthAndYear_formatsCorrectly() {
        assertEquals("October 2026", DateFormatter.monthAndYear(testDate))
    }

    @Test
    fun timelineDate_formatsCorrectly() {
        assertEquals("Wed, Oct 28, 2026", DateFormatter.timelineDate(testDate))
        assertEquals("Wed, Oct 28, 2026", DateFormatter.timelineDate(testDate.toEpochDay()))
    }

    @Test
    fun shortDate_formatsCorrectly() {
        assertEquals("Oct 28, 2026", DateFormatter.shortDate(testDate))
        assertEquals("Oct 28, 2026", DateFormatter.shortDate(testDate.toEpochDay()))
    }

    @Test
    fun epochMillis_convertsBidirectionally() {
        val millis = DateFormatter.toEpochMillis(testDate)
        val convertedDate = DateFormatter.fromEpochMillis(millis)
        assertEquals(testDate, convertedDate)
    }
}
