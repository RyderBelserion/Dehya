package net.dehya.dehyabot.events.emojis

import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class PumpkinPostEvent : ListenerAdapter() {

    @Override
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message

        val emoji = Emoji.fromCustom("pumpkin", 1032455289422745691, false)

        if (message.contentStripped.contains("pumpkin", true)) message.addReaction(emoji).queue()
    }
}