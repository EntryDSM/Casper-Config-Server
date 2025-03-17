package hs.kr.casper.configserver.adapter.`in`.env.dto.response

import java.util.*

data class EnvironmentValueResponse(
    val id: UUID,
    val application: String,
    val profile: String,
    val label: String,
    val key: String,
    val value: String
)