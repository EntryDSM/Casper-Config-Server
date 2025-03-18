package hs.kr.casper.configserver.adapter.`in`.env.dto.request

data class EnvironmentValuesRequest(
    val application: String,
    val profile: String,
    val label: String,
    val properties: Map<String, String>
)