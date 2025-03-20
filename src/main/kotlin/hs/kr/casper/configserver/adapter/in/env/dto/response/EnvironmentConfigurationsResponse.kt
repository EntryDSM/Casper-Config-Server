package hs.kr.casper.configserver.adapter.`in`.env.dto.response

data class EnvironmentConfigurationsResponse(
    val application: String,
    val profile: String,
    val label: String,
    val properties: Map<String, String>,
)