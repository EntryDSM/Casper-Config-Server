package hs.kr.casper.configserver.adapter.`in`.env

import hs.kr.casper.configserver.adapter.`in`.env.dto.request.EnvironmentConfigurationRequest
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import hs.kr.casper.configserver.application.env.port.`in`.EnvironmentConfigurationUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/env")
class EnvironmentValueWebAdapter(
    private val environmentConfigurationUseCase: EnvironmentConfigurationUseCase
) {
    @PostMapping
    fun storeEnvironmentConfiguration(
        @RequestBody
        environmentConfigurationRequest: EnvironmentConfigurationRequest
    ): ResponseEntity<EnvironmentOperationResponse> {
        val response = environmentConfigurationUseCase.storeConfiguration(
            application = environmentConfigurationRequest.application,
            profile = environmentConfigurationRequest.profile,
            label = environmentConfigurationRequest.label,
            properties = environmentConfigurationRequest.properties
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping
    fun updateEnvironmentConfiguration(
        @RequestBody
        environmentConfigurationRequest: EnvironmentConfigurationRequest
    ): ResponseEntity<EnvironmentOperationResponse> {
        val response = environmentConfigurationUseCase.updateConfiguration(
            application = environmentConfigurationRequest.application,
            profile = environmentConfigurationRequest.profile,
            label = environmentConfigurationRequest.label,
            properties = environmentConfigurationRequest.properties
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{application}/{profile}/{label}")
    fun retrieveEnvironmentConfigurations(
        @PathVariable
        application: String,
        @PathVariable
        profile: String,
        @PathVariable
        label: String
    ): ResponseEntity<EnvironmentConfigurationResponse> {
        val response = environmentConfigurationUseCase.retrieveEnvironmentConfigurations(
            application = application,
            profile = profile,
            label = label
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{application}/{profile}/{label}/{key}")
    fun retrieveEnvironmentConfiguration(
        @PathVariable
        application: String,
        @PathVariable
        profile: String,
        @PathVariable
        label: String,
        @PathVariable
        key: String
    ): ResponseEntity<EnvironmentConfigurationResponse> {
        val response = environmentConfigurationUseCase.retrieveEnvironmentConfiguration(
            application = application,
            profile = profile,
            label = label,
            key = key
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{application}/{profile}/{label}")
    fun removeEnvironmentConfigurations(
        @PathVariable
        application: String,
        @PathVariable
        profile: String,
        @PathVariable
        label: String
    ): ResponseEntity<EnvironmentOperationResponse> {
        val response = environmentConfigurationUseCase.removeConfigurations(
            application = application,
            profile = profile,
            label = label
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{application}/{profile}/{label}/{key}")
    fun removeEnvironmentConfiguration(
        @PathVariable
        application: String,
        @PathVariable
        profile: String,
        @PathVariable
        label: String,
        @PathVariable
        key: String
    ): ResponseEntity<EnvironmentOperationResponse> {
        val response = environmentConfigurationUseCase.removeConfiguration(
            application = application,
            profile = profile,
            label = label,
            key = key
        )
        return ResponseEntity.ok(response)
    }
}