package hs.kr.casper.configserver.infrastructure.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api")
data class ApiKeyProperties(
    val accessKey: String
)