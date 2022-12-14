dependencyResolutionManagement {
    includeBuild("build-logic")
}

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "Dehya"

include("discord")