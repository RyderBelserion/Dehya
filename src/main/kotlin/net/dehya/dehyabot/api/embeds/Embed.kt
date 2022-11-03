package net.dehya.dehyabot.api.embeds

import net.dehya.dehyabot.api.toColor
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.entities.User

class Embed {

    private val embed = EmbedBuilder()
    private val fields = Fields(embed)

    init {
        embed.setColor(EmbedColors.DEFAULT.code.toColor())
    }

    /**
     * Set the default title.
     */
    fun setTitle(title: String) {
        embed.setTitle(title)
    }

    /**
     * Set the footer using strings.
     * @param footer - The text to put in the footer.
     * @param icon - The icon whether it's a profile picture or something random.
     */
    fun setFooter(footer: String, icon: String? = null) {
        embed.setFooter(footer, icon)
    }

    /**
     * Set the footer using the user object.
     * @param user - The member in question.
     */
    fun setFooter(user: User, guild: Guild) {
        val avatar = guild.getMember(user)?.effectiveAvatarUrl

        embed.setFooter("Requested by: ${user.asMention}", avatar)
    }

    /**
     * Add a list of fields.
     */
    fun addFields(block: Fields.() -> Unit) {
        block(fields)
    }

    /**
     * Set the hex color to a color of our choice.
     * @param hex - The color to choose.
     */
    fun setColor(hex: String) {
        embed.setColor(hex.toColor())
    }

    /**
     * Set a color using one of our pre-set colors.
     * @param color - A preset enum of colors.
     */
    fun setColor(color: EmbedColors) {
        embed.setColor(color.code.toColor())
    }

    /**
     * Set thumbnails using strings.
     * @param url - The image url to use.
     */
    fun setThumbnail(url: String) {
        embed.setThumbnail(url)
    }

    /**
     * Set the thumbnail using the user object.
     * @param user - The member in question.
     * @param guild - Used to fetch the member's guild avatar otherwise fetches global avatar.
     */
    fun setThumbnail(user: User, guild: Guild?) {
        val avatar = guild?.getMember(user)?.effectiveAvatarUrl

        embed.setThumbnail(avatar)
    }

    /**
     * Set the image using a string.
     * @param url - The image url to use.
     */
    fun setImage(url: String) {
        embed.setImage(url)
    }

    /**
     * Set the author using strings.
     * @param name - The name of the author.
     * @param image - An optional value to input an image url.
     */
    fun setAuthor(name: String, image: String? = null) {
        embed.setAuthor(name, null, image)
    }

    /**
     * Set the author using the user object.
     * @param user - The member in question.
     * @param guild - Used to fetch the member's guild avatar otherwise fetches global avatar.
     */
    fun setAuthor(user: User, guild: Guild?) {
        val avatar = guild?.getMember(user)?.effectiveAvatarUrl

        embed.setAuthor(user.asMention, null, avatar)
    }

    /**
     * Set the description of the embed.
     * @param description - The description to use.
     */
    fun setDescription(description: String) {
        embed.setDescription(description)
    }

    fun build(): MessageEmbed {
        return embed.build()
    }
}

/**
 * Build the embed.
 */
inline fun embed(builder: Embed.() -> Unit): MessageEmbed {
    return Embed().apply(builder).build()
}