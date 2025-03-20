package hs.kr.casper.configserver.application.env.port.`in`

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationsResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse

interface EnvironmentConfigurationUseCase {
    fun retrieveConfiguration(application: String, profile: String, label: String): EnvironmentConfigurationsResponse

    fun storeConfiguration(application: String, profile: String, label: String, properties: Map<String, String>): EnvironmentOperationResponse

    fun removeConfiguration(application: String, profile: String, label: String): EnvironmentOperationResponse
}