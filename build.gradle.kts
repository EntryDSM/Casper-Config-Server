import org.apache.tools.ant.taskdefs.optional.depend.Depend

plugins {
    kotlin(Plugins.Kotlin.JVM) version Versions.KOTLIN
    kotlin(Plugins.Kotlin.SPRING) version Versions.KOTLIN
    id(Plugins.SPRING_BOOT) version Versions.SPRING_BOOT
    id(Plugins.SPRING_DEPENDENCY_MANAGEMENT) version Versions.SPRING_DEPENDENCY_MANAGEMENT
}

group = "hs.kr.casper"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(Versions.JAVA_VERSION)
    }
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD}")
    }
}

dependencies {
    implementation(Dependencies.Spring.BOOT_STARTER)
    implementation(Dependencies.Kotlin.REFLECT)

    implementation(Dependencies.SpringCloud.CONFIG_SERVER)
    implementation(Dependencies.Spring.BOOT_ACTUATOR)
    implementation(Dependencies.SpringCloud.BOOTSTRAP)

    implementation(Dependencies.JPA.SPRING_DATA_JPA)
    implementation(Dependencies.Lombok.LOMBOK)

    runtimeOnly(Dependencies.MySQL.CONNECTOR_JAVA)

    testImplementation(Dependencies.Spring.BOOT_TEST)
    testImplementation(Dependencies.Kotlin.TEST_JUNIT5)
    testRuntimeOnly(Dependencies.Testing.JUNIT_PLATFORM_LAUNCHER)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}