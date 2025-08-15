package hs.kr.casper.configserver.infrastructure.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val secretKey: String,
    val accessTokenExpiration: Long = 3600000,
    val issuer: String = "casper-config-server"
)