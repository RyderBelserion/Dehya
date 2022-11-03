package net.dehya.dehyabot.commands.user

import net.dehya.dehyabot.api.embeds.EmbedColors
import net.dehya.dehyabot.api.embeds.embed
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class AvatarCommand : ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != "avatar") return

        val guildAvatar = event.guild?.getMember(event.user)

        val embed = embed {
            setAuthor(event.user, event.guild)

            setColor(EmbedColors.DEFAULT)

            setImage("${event.user.avatarUrl}?size=512")

            setThumbnail(event.user, event.guild)

            addFields {
                field("Guild Avatar", "[Click Me](${guildAvatar?.avatarUrl})", false)
            }
        }

        event.replyEmbeds(embed).queue()
    }
}