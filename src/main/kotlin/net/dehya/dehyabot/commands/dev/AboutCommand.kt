package net.dehya.dehyabot.commands.dev

import net.dehya.dehyabot.api.embeds.EmbedColors
import net.dehya.dehyabot.api.embeds.embed
import net.dehya.dehyabot.api.guildCount
import net.dehya.dehyabot.api.memberCount
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class AboutCommand : ListenerAdapter() {

    @Override
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != "about") return

        val embed = embed {

            setAuthor(event.user, event.guild)

            setDescription("""
                <a:hello:1030500460873924648>, My name is Dehya... Here you will find some information about me.
                
                I am written using [Kotlin](https://kotlinlang.org/) with the library Discord JDA
                You can report/suggest features or read my code @ [github](https://github.com/dehyabot/Dehya)
            """.trimIndent())

            setColor(EmbedColors.DEFAULT)

            addFields {
                field("<:pumpkin:1032455289422745691> Pumpkins: ", "`0`", true)
                field("<:holyduck:1032464349505278103> Ducks: ", "`0`", true)
                field(":ping_pong: Ping", "`${event.jda.gatewayPing}ms`", true)
                field(":homes: Guilds", "${event.jda.guildCount}", true)
                field("<:dunce:1015324356018176071> Members", "${event.jda.memberCount}", true)
                field("<:ruby:1016217265907191858> Version", "`0.0.1`", true)
            }
        }

        event.replyEmbeds(embed)
    }
}