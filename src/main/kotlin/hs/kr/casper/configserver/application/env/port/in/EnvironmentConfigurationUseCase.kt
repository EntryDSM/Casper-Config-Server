package hs.kr.casper.configserver.application.env.port.`in`

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import java.util.*

interface EnvironmentConfigurationUseCase {
    fun retrieveEnvironmentConfigurations(application: String, profile: String, label: String, userId: UUID, isAdmin: Boolean): EnvironmentConfigurationResponse

    fun retrieveEnvironmentConfiguration(application: String, profile: String, label: String, key: String, userId: UUID, isAdmin: Boolean): EnvironmentConfigurationResponse

    fun storeConfiguration(application: String, profile: String, label: String, properties: Map<String, String>, userId: UUID): EnvironmentOperationResponse

    fun removeConfigurations(application: String, profile: String, label: String, userId: UUID, isAdmin: Boolean): EnvironmentOperationResponse

    fun removeConfiguration(application: String, profile: String, label: String, key: String, userId: UUID, isAdmin: Boolean): EnvironmentOperationResponse
}