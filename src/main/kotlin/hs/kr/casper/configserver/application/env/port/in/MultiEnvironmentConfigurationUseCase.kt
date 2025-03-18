package hs.kr.casper.configserver.application.env.port.`in`

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse

interface MultiEnvironmentConfigurationUseCase {
    fun retrieveConfigurations(application: String, profile: String, label: String): EnvironmentConfigurationResponse

    fun storeConfigurations(application: String, profile: String, label: String): EnvironmentOperationResponse

    fun removeConfigurations(application: String, profile: String, label: String): EnvironmentOperationResponse
}