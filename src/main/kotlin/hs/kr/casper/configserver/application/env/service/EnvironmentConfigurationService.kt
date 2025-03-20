package hs.kr.casper.configserver.application.env.service

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationsResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import hs.kr.casper.configserver.application.env.port.`in`.EnvironmentConfigurationUseCase
import hs.kr.casper.configserver.application.env.port.out.RetrieveConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.StoreConfigurationPort
import hs.kr.casper.configserver.domain.env.model.EnvironmentConfiguration
import hs.kr.casper.configserver.domain.env.model.enum.EnvironmentOperationType
import org.springframework.stereotype.Service

@Service
class EnvironmentConfigurationService(
    private val retrieveConfigurationPort: RetrieveConfigurationPort,
    private val storeConfigurationPort: StoreConfigurationPort
): EnvironmentConfigurationUseCase {

    override fun storeConfiguration(
        application: String,
        profile: String,
        label: String,
        properties: Map<String, String>
    ): EnvironmentOperationResponse {

        properties.forEach { (key, value) ->
            storeConfigurationPort.storeConfiguration(
                EnvironmentConfiguration(
                    application = application,
                    profile = profile,
                    label = label,
                    key = key,
                    value = value
                )
            )
        }

        return EnvironmentOperationResponse(
            success = true,
            operation = EnvironmentOperationType.STORE
        )
    }

    override fun removeConfiguration(
        application: String,
        profile: String,
        label: String
    ): EnvironmentOperationResponse {
        TODO("Not yet implemented")
    }

    override fun retrieveConfiguration(
        application: String,
        profile: String,
        label: String
    ): EnvironmentConfigurationsResponse {
        TODO("Not yet implemented")
    }
}