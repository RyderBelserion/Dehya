plugins {
    kotlin("jvm") version "1.7.20"

    id("com.github.johnrengelman.shadow") version "7.1.2"

    application
}

val buildNumber: String? = System.getenv("BUILD_NUMBER")

val jenkinsVersion = "0.0.1-b$buildNumber"

group = "net.dehya.dehyabot"
version = "0.0.1"
description = "A discord bot in kotlin using the Discord JDA library."

application {
    mainClass.set("net.dehya.dehyabot.Dehya")
}

repositories {
    mavenCentral()
}

dependencies {
    // Discord JDA
    implementation("net.dv8tion:JDA:5.0.0-alpha.22")

    implementation("io.github.cdimascio:dotenv-kotlin:6.3.1")

    implementation("ch.qos.logback:logback-classic:1.4.4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation(kotlin("stdlib", "1.7.20"))
}

tasks {
    shadowJar {
        if (buildNumber != null) {
            archiveFileName.set("${rootProject.name}-[v${jenkinsVersion}].jar")
        } else {
            archiveFileName.set("${rootProject.name}-[v${rootProject.version}].jar")
        }

        listOf(
            "net.dv8tion"
        ).onEach {
            relocate(it, "${group}.libs.$it")
        }
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}