package hs.kr.casper.configserver.application.env.port.`in`

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse

interface MultiEnvironmentConfigurationUseCase {
    fun retrieveConfiguration(application: String, profile: String, label: String): EnvironmentConfigurationResponse

    fun storeConfiguration(application: String, profile: String, label: String): EnvironmentOperationResponse

    fun removeConfiguration(application: String, profile: String, label: String): EnvironmentOperationResponse
}