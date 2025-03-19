package hs.kr.casper.configserver.application.env.service

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentValueResponse
import hs.kr.casper.configserver.application.env.port.`in`.MultiEnvironmentConfigurationUseCase
import hs.kr.casper.configserver.application.env.port.`in`.SingleEnvironmentConfigurationUseCase
import org.springframework.stereotype.Service

@Service
class EnvironmentConfigurationService(

): MultiEnvironmentConfigurationUseCase, SingleEnvironmentConfigurationUseCase {
    override fun storeConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String,
        value: String
    ): EnvironmentOperationResponse {
        TODO("Not yet implemented")
    }

    override fun removeConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String
    ): EnvironmentOperationResponse {
        TODO("Not yet implemented")
    }

    override fun retrieveConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String
    ): EnvironmentValueResponse {
        TODO("Not yet implemented")
    }

    override fun removeConfigurations(
        application: String,
        profile: String,
        label: String
    ): EnvironmentOperationResponse {
        TODO("Not yet implemented")
    }

    override fun retrieveConfigurations(
        application: String,
        profile: String,
        label: String
    ): EnvironmentConfigurationResponse {
        TODO("Not yet implemented")
    }

    override fun storeConfigurations(
        application: String,
        profile: String,
        label: String
    ): EnvironmentOperationResponse {
        TODO("Not yet implemented")
    }
}