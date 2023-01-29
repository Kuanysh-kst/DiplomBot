plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"

}

group = "kz.kuanysh.bot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.telegram:telegrambots:6.0.1")
    implementation("ch.qos.logback:logback-classic:1.2.11")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

//tasks.jar{
//    mainfest.attributes["Main-Class"] = "kz.kuanysh.bot.Main"
//}

