plugins {
    id("dehya-language")

    application
}

application {
    mainClass.set("net.dehya.dehyabot.Dehya")
}

dependencies {
    implementation("net.dv8tion", "JDA", "5.0.0-beta.1")

    implementation("ch.qos.logback", "logback-classic", "1.4.5")

    implementation("org.jetbrains.exposed", "exposed-core", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.40.1")
}