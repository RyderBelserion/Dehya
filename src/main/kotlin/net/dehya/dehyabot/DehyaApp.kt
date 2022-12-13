package net.dehya.dehyabot

import net.dehya.dehyabot.api.Application
import net.dehya.dehyabot.api.schedules.Scheduler
import net.dehya.dehyabot.api.updatePresence
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.requests.GatewayIntent
import kotlin.time.Duration.Companion.minutes

private val intents = listOf(
    /**
     * Guild Integrations
     */
    GatewayIntent.GUILD_BANS,
    GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
    GatewayIntent.GUILD_VOICE_STATES,
    GatewayIntent.GUILD_MESSAGES,
    GatewayIntent.GUILD_MESSAGE_REACTIONS,

    /**
     * Special Intents
     */
    GatewayIntent.GUILD_MEMBERS,
    GatewayIntent.MESSAGE_CONTENT,
    GatewayIntent.SCHEDULED_EVENTS
)

class DehyaApp(token: String): Application(token, intents) {

    override fun onStart() {

    }

    override fun onReady() {

    }

    override fun onGuildReady(guild: Guild) {
        Scheduler.interval(2.minutes, task = { updatePresence(guild.jda) })
    }

    override fun onStop() {

    }
}