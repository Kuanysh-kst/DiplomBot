plugins {
    java
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version ("1.0.15.RELEASE")
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "kz.kuanysh.bot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc" )
    implementation("org.telegram:telegrambots:6.5.0")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("org.postgresql:postgresql:42.5.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

springBoot {
    mainClass.set("kz.kuanysh.bot.Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "kz.kuanysh.bot.Main"
    }
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}