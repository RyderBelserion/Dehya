plugins {
    id("dehya-base")

    id("com.github.johnrengelman.shadow") version "7.1.2"
}

tasks {
    shadowJar {
        archiveFileName.set("${project.name}-${project.version}.jar")
    }
}