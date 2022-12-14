package net.dehya.dehyabot.api

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Activity
import java.awt.Color

fun updatePresence(api: JDA) {
    when ((0..2).random()) {
        0 -> api.presence.setPresence(Activity.playing("Version 0.0.1"), false)
        1 -> api.presence.setPresence(Activity.watching("${api.memberCount} members."), false)
        2 -> api.presence.setPresence(Activity.listening("${api.guildCount} guilds."), false)
    }
}

fun String.toColor(): Color {
    return Color(
        Integer.valueOf(substring(1, 3), 16),
        Integer.valueOf(substring(3, 5), 16),
        Integer.valueOf(substring(5, 7), 16)
    )
}

val JDA.memberCount: Int get() = guilds.sumOf { it.memberCount }

val JDA.guildCount: Int get() = guilds.size