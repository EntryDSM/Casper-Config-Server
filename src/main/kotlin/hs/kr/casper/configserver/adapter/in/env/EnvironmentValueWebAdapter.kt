package hs.kr.casper.configserver.adapter.`in`.env

import hs.kr.casper.configserver.adapter.`in`.env.dto.request.EnvironmentConfigurationRequest
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import hs.kr.casper.configserver.application.env.port.`in`.EnvironmentConfigurationUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/api/env")
class EnvironmentValueWebAdapter(
    private val environmentConfigurationUseCase: EnvironmentConfigurationUseCase
) {
    @PostMapping
    fun storeEnvironmentConfiguration(
        @RequestBody
        environmentConfigurationRequest: EnvironmentConfigurationRequest,
        authentication: Authentication
    ): ResponseEntity<EnvironmentOperationResponse> {
        val userId = UUID.fromString(authentication.name)
        val response = environmentConfigurationUseCase.storeConfiguration(
            application = environmentConfigurationRequest.application,
            profile = environmentConfigurationRequest.profile,
            label = environmentConfigurationRequest.label,
            properties = environmentConfigurationRequest.properties,
            userId = userId
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{application}/{profile}/{label}")
    fun retrieveEnvironmentConfigurations(
        @PathVariable
        application: String,
        @PathVariable
        profile: String,
        @PathVariable
        label: String,
        authentication: Authentication
    ): ResponseEntity<EnvironmentConfigurationResponse> {
        val userId = UUID.fromString(authentication.name)
        val isAdmin = authentication.authorities.contains(SimpleGrantedAuthority("ROLE_CONFIG_ADMIN"))
        val response = environmentConfigurationUseCase.retrieveEnvironmentConfigurations(
            application = application,
            profile = profile,
            label = label,
            userId = userId,
            isAdmin = isAdmin
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
        key: String,
        authentication: Authentication
    ): ResponseEntity<EnvironmentConfigurationResponse> {
        val userId = UUID.fromString(authentication.name)
        val isAdmin = authentication.authorities.contains(SimpleGrantedAuthority("ROLE_CONFIG_ADMIN"))
        val response = environmentConfigurationUseCase.retrieveEnvironmentConfiguration(
            application = application,
            profile = profile,
            label = label,
            key = key,
            userId = userId,
            isAdmin = isAdmin
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
        label: String,
        authentication: Authentication
    ): ResponseEntity<EnvironmentOperationResponse> {
        val userId = UUID.fromString(authentication.name)
        val isAdmin = authentication.authorities.contains(SimpleGrantedAuthority("ROLE_CONFIG_ADMIN"))
        val response = environmentConfigurationUseCase.removeConfigurations(
            application = application,
            profile = profile,
            label = label,
            userId = userId,
            isAdmin = isAdmin
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
        key: String,
        authentication: Authentication
    ): ResponseEntity<EnvironmentOperationResponse> {
        val userId = UUID.fromString(authentication.name)
        val isAdmin = authentication.authorities.contains(SimpleGrantedAuthority("ROLE_CONFIG_ADMIN"))
        val response = environmentConfigurationUseCase.removeConfiguration(
            application = application,
            profile = profile,
            label = label,
            key = key,
            userId = userId,
            isAdmin = isAdmin
        )
        return ResponseEntity.ok(response)
    }
}