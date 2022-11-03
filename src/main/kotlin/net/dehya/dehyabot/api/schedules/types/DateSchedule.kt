package net.dehya.dehyabot.api.schedules.types

import net.dehya.dehyabot.api.schedules.Schedule
import java.time.LocalDateTime

/**
 * A schedule that is based on a date.
 */
class DateSchedule(
    private val dateTime: LocalDateTime,
    private val block: suspend () -> Unit,
) : Schedule {

    // Will check if the date time is valid or not.
    init {
        if (dateTime.isBefore(LocalDateTime.now())) throw IllegalArgumentException("DateTime must be after now")
    }

    override val isRepeating: Boolean = false

    override suspend fun execute() {
        block()
    }

    override fun shouldRun(nowDateTime: LocalDateTime): Boolean {
        return nowDateTime.isEqual(dateTime)
    }
}