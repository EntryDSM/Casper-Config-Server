package hs.kr.casper.configserver.adapter.`in`.env.dto.request

data class EnvironmentConfigurationRequest(
    val application: String,
    val profile: String,
    val label: String,
    val properties: Map<String, String>
)