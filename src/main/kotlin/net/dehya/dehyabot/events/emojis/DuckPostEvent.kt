package net.dehya.dehyabot.events.emojis

import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class DuckPostEvent : ListenerAdapter() {

    @Override
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message

        val emoji = Emoji.fromCustom("holyduck", 1032464349505278103, false)

        if (message.contentStripped.contains("duck", true)) message.addReaction(emoji).queue()
    }
}