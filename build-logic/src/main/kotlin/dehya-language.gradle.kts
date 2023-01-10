import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

repositories {
    maven("https://repo.crazycrew.us/plugins/")

    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib", project.extra["kotlin_version"] as String))
}

kotlin {
    jvmToolchain {
        this.apply {
            languageVersion.set(JavaLanguageVersion.of(project.extra["java_version"].toString()))
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = project.extra["java_version"].toString()
            javaParameters = true
        }
    }
}