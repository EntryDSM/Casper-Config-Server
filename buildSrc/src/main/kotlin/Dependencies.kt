object Dependencies {
    object Kotlin {
        const val REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
        const val TEST_JUNIT5 = "org.jetbrains.kotlin:kotlin-test-junit5"
    }

    object Spring {
        const val BOOT_STARTER = "org.springframework.boot:spring-boot-starter"
        const val BOOT_TEST = "org.springframework.boot:spring-boot-starter-test"
        const val BOOT_ACTUATOR = "org.springframework.boot:spring-boot-starter-actuator"
    }

    object SpringCloud {
        const val CONFIG_SERVER = "org.springframework.cloud:spring-cloud-config-server"
        const val BOOTSTRAP = "org.springframework.cloud:spring-cloud-starter-bootstrap"
    }

    object JPA {
        const val SPRING_DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    }

    object MySQL {
        const val CONNECTOR_JAVA = "com.mysql:mysql-connector-j"
    }

    object Testing {
        const val JUNIT_PLATFORM_LAUNCHER = "org.junit.platform:junit-platform-launcher"
    }
}