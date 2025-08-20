package hs.kr.casper.configserver.domain.env.model

import java.util.*

data class EnvironmentConfiguration(
    val id: UUID = UUID.randomUUID(),
    val application: String,
    val profile: String,
    val label: String,
    val key: String,
    val value: String
)