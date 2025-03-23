package hs.kr.casper.configserver.application.env.port.`in`

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse

interface EnvironmentConfigurationUseCase {
    fun retrieveEnvironmentConfigurations(application: String, profile: String, label: String): EnvironmentConfigurationResponse

    fun retrieveEnvironmentConfiguration(application: String, profile: String, label: String, key: String): EnvironmentConfigurationResponse

    fun storeConfiguration(application: String, profile: String, label: String, properties: Map<String, String>): EnvironmentOperationResponse

    fun removeConfigurations(application: String, profile: String, label: String): EnvironmentOperationResponse

    fun removeConfiguration(application: String, profile: String, label: String, key: String): EnvironmentOperationResponse
}