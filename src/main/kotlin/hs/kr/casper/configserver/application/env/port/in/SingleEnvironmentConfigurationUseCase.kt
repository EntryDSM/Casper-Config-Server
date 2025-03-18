package hs.kr.casper.configserver.application.env.port.`in`

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentValueResponse

interface SingleEnvironmentConfigurationUseCase {
    fun retrieveConfiguration(application: String, profile: String, label: String, key: String): EnvironmentValueResponse

    fun storeConfiguration(application: String, profile: String, label: String, key: String, value: String): EnvironmentOperationResponse

    fun removeConfiguration(application: String, profile: String, label: String, key: String): EnvironmentOperationResponse
}