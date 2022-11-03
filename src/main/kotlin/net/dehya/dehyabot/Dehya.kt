package net.dehya.dehyabot

import io.github.cdimascio.dotenv.dotenv

fun main() {
    val env = dotenv()

    val token = env["DISCORD_TOKEN"]

    DehyaApp(token)
}