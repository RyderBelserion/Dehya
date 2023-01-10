dependencyResolutionManagement {
    includeBuild("build-logic")
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("jvm").version("1.8.0")
    }
}

include("discord")