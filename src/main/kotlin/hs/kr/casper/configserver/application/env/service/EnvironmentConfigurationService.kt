package hs.kr.casper.configserver.application.env.service

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import hs.kr.casper.configserver.application.env.port.`in`.EnvironmentConfigurationUseCase
import hs.kr.casper.configserver.application.env.port.out.EncryptionPort
import hs.kr.casper.configserver.application.env.port.out.ExistsConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.RemoveConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.RetrieveConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.StoreConfigurationPort
import hs.kr.casper.configserver.domain.env.model.EnvironmentConfiguration
import hs.kr.casper.configserver.domain.env.model.enum.EnvironmentOperationType
import hs.kr.casper.configserver.infrastructure.error.message.ErrorMessages
import hs.kr.casper.configserver.infrastructure.exception.EntryHttpException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EnvironmentConfigurationService(
    private val retrieveConfigurationPort: RetrieveConfigurationPort,
    private val storeConfigurationPort: StoreConfigurationPort,
    private val removeConfigurationPort: RemoveConfigurationPort,
    private val existsConfigurationPort: ExistsConfigurationPort,
    private val encryptionPort: EncryptionPort
): EnvironmentConfigurationUseCase {

    @Transactional
    override fun storeConfiguration(
        application: String,
        profile: String,
        label: String,
        properties: Map<String, String>
    ): EnvironmentOperationResponse {
        val duplicateKeys = properties.keys.filter { key ->
            existsConfigurationPort.existsConfiguration(application, profile, label, key)
        }

        if (duplicateKeys.isNotEmpty()) {
            throw EntryHttpException.conflict(ErrorMessages.ENTRY_CONFLICT)
        }

        properties.entries.forEach { (key, value) ->
            val encryptedValue = if (!encryptionPort.isEncrypted(value)) {
                encryptionPort.encrypt(value)
            } else {
                value
            }

            storeConfigurationPort.storeConfiguration(
                EnvironmentConfiguration(
                    application = application,
                    profile = profile,
                    label = label,
                    key = key,
                    value = encryptedValue
                )
            )
        }

        return EnvironmentOperationResponse(
            operation = EnvironmentOperationType.STORE
        )
    }

    @Transactional(readOnly = true)
    override fun retrieveEnvironmentConfigurations(
        application: String,
        profile: String,
        label: String
    ): EnvironmentConfigurationResponse {
        val configurations = retrieveConfigurationPort.retrieveConfigurations(
            application = application,
            profile = profile,
            label = label,
        )

        if (configurations.isEmpty()) {
            throw EntryHttpException.notFound(ErrorMessages.ENTRY_NOT_FOUND)
        }

        return EnvironmentConfigurationResponse(
            application = application,
            profile = profile,
            label = label,
            properties = configurations
        )
    }

    @Transactional(readOnly = true)
    override fun retrieveEnvironmentConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String
    ): EnvironmentConfigurationResponse {
        val configuration = retrieveConfigurationPort.retrieveConfiguration(
            application = application,
            profile = profile,
            label = label,
            key = key
        )

        if (configuration.isEmpty()) {
            throw EntryHttpException.notFound(ErrorMessages.ENTRY_NOT_FOUND)
        }

        return EnvironmentConfigurationResponse(
            application = application,
            profile = profile,
            label = label,
            properties = configuration
        )
    }

    @Transactional
    override fun removeConfigurations(
        application: String,
        profile: String,
        label: String
    ): EnvironmentOperationResponse {
        val configurations = retrieveConfigurationPort.retrieveConfigurations(
            application = application,
            profile = profile,
            label = label
        )

        if (configurations.isEmpty()) {
            throw EntryHttpException.notFound(ErrorMessages.ENTRY_NOT_FOUND)
        }

        configurations.keys.forEach { key ->
            removeConfigurationPort.removeConfiguration(
                EnvironmentConfiguration(
                    application = application,
                    profile = profile,
                    label = label,
                    key = key,
                    value = configurations.getValue(key)
                )
            )
        }

        return EnvironmentOperationResponse(
            operation = EnvironmentOperationType.REMOVE
        )
    }

    @Transactional
    override fun removeConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String
    ): EnvironmentOperationResponse {
        val configuration = retrieveConfigurationPort.retrieveConfiguration(
            application = application,
            profile = profile,
            label = label,
            key = key
        )

        if (configuration.isEmpty()) {
            throw EntryHttpException.notFound(ErrorMessages.ENTRY_NOT_FOUND)
        }

        removeConfigurationPort.removeConfiguration(
            EnvironmentConfiguration(
                application = application,
                profile = profile,
                label = label,
                key = key,
                value = configuration[key]!!
            )
        )

        return EnvironmentOperationResponse(
            operation = EnvironmentOperationType.REMOVE
        )
    }
}