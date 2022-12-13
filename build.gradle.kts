plugins {
    kotlin("jvm") version "1.7.21"

    id("com.github.johnrengelman.shadow") version "7.1.2"

    application
}

val buildNumber: String? = System.getenv("BUILD_NUMBER")

project.group = "net.dehya.dehyabot"
project.version = "0.0.1"
project.description = "A discord bot in kotlin using the Discord JDA library."

val buildVersion = "${project.version}-b$buildNumber"

application {
    mainClass.set("net.dehya.dehyabot.Dehya")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion", "JDA", "5.0.0-beta.1")

    implementation("ch.qos.logback", "logback-classic", "1.4.5")

    implementation("io.github.cdimascio", "dotenv-kotlin", "6.4.0")

    implementation("org.jetbrains.exposed", "exposed-core", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.40.1")

    implementation(kotlin("stdlib", "1.7.21"))
}

tasks {
    shadowJar {
        if (buildNumber != null) {
            archiveFileName.set("${rootProject.name}-${buildVersion}.jar")
        } else {
            archiveFileName.set("${rootProject.name}-${project.version}.jar")
        }

        //listOf(
        //    "net.dv8tion"
        //).onEach {
        //    relocate(it, "${group}.libs.$it")
        //}
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}