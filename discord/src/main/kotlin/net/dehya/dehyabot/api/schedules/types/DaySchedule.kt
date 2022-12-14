package net.dehya.dehyabot.api.schedules.types

import net.dehya.dehyabot.api.schedules.Schedule
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

class DaySchedule(
    private val days: Set<DayOfWeek>,
    private val time: LocalTime,
    override val isRepeating: Boolean = true,
    private val block: suspend () -> Unit,
) : Schedule {

    override suspend fun execute() {
        block()
    }

    override fun shouldRun(nowDateTime: LocalDateTime): Boolean {
        if (nowDateTime.dayOfWeek !in days) return false

        return nowDateTime.toLocalTime() == time
    }
}