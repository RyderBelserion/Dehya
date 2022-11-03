package net.dehya.dehyabot.api

import net.dehya.dehyabot.api.schedules.Scheduler
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.events.session.ShutdownEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent

abstract class Application(
    private val token: String,
    private val intents: List<GatewayIntent> = emptyList(),
    private val extra: JDABuilder.() -> Unit = {}
): ModularApplication {

    private var api: JDA? = null

    init {
        api = start()

        Scheduler.start()
    }

    override fun onStart() {}

    override fun onStop() {}

    override fun onReady() {}

    override fun onGuildReady(guild: Guild) {}

    /**
     * Register a listener.
     */
    private fun register(listener: ListenerAdapter) {
        api?.addEventListener(listener)
    }

    /**
     * Registers multiple listeners.
     */
    fun register(vararg listeners: ListenerAdapter) {
        listeners.forEach(::register)
    }

    private fun start(): JDA {
        return JDABuilder.createDefault(token, intents).addEventListeners(ApplicationListener(this)).apply(extra).build()
    }

    private class ApplicationListener(private val application: Application): ListenerAdapter() {

        override fun onReady(event: ReadyEvent) {
            application.onReady()
        }

        override fun onShutdown(event: ShutdownEvent) {
            application.onStop()
        }

        override fun onGuildReady(event: GuildReadyEvent) {
            application.onGuildReady(event.guild)
        }
    }
}

interface ModularApplication {

    fun onStart()

    fun onStop()

    fun onReady()

    fun onGuildReady(guild: Guild)

}