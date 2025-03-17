package hs.kr.casper.configserver.adapter.`in`.env.dto.request

data class EnvironmentValueRequest(
    val application: String,
    val profile: String,
    val label: String,
    val key: String,
    val value: String
)