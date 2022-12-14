package net.dehya.dehyabot.api.schedules

import kotlinx.coroutines.*
import net.dehya.dehyabot.api.schedules.types.DateSchedule
import net.dehya.dehyabot.api.schedules.types.DaySchedule
import java.time.Clock
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

object Scheduler : CoroutineScope {

    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private val job = Job()

    override val coroutineContext: CoroutineContext get() = job + dispatcher

    private val schedules = mutableListOf<Schedule>()
    private var clock = Clock.systemDefaultZone()
    private var isStarted = false

    fun interval(period: Duration, delay: Duration = 0.seconds, task: suspend () -> Unit) {
        schedules.add(ScheduleTimer(period.inWholeSeconds, delay.inWholeSeconds, true, task))
    }

    fun interval(days: Set<DayOfWeek>, at: LocalTime, task: suspend () -> Unit) {
        schedules.add(DaySchedule(days, at, true, task))
    }

    fun at(date: LocalDateTime, task: suspend () -> Unit) {
        schedules.add(DateSchedule(date, task))
    }

    fun countdown(time: Duration, task: suspend () -> Unit) {
        schedules.add(ScheduleTimer(0, time.inWholeSeconds, false, task))
    }

    /**
     * Start the timer logic.
     */
    fun start(): Scheduler {
        launch {
            var lastCheck: LocalDateTime? = null

            while (true) {
                val second = LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS)

                if (lastCheck != second) {
                    lastCheck = second

                    coroutineScope {
                        launchSchedules(second)
                    }
                }

                delay(50)
            }
        }

        isStarted = true

        return this
    }

    /**
     * Launches each schedule.
     */
    private fun launchSchedules(nowMinute: LocalDateTime) {
        schedules.forEach {
            launch {
                if (!it.shouldRun(nowMinute)) return@launch

                it.execute()

                if (!it.isRepeating) schedules.remove(it)
            }
        }
    }
}

interface Schedule {
    val isRepeating: Boolean

    suspend fun execute()

    fun shouldRun(nowDateTime: LocalDateTime): Boolean
}