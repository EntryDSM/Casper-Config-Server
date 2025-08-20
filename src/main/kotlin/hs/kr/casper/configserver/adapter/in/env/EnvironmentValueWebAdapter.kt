package hs.kr.casper.configserver.adapter.`in`.env

import hs.kr.casper.configserver.adapter.`in`.env.dto.request.EnvironmentConfigurationRequest
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentConfigurationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import hs.kr.casper.configserver.application.env.port.`in`.EnvironmentConfigurationUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Environment Configuration", description = "환경 설정 관리 API")
class EnvironmentValueWebAdapter(
    private val environmentConfigurationUseCase: EnvironmentConfigurationUseCase
) {
    @PostMapping
    @Operation(summary = "환경 설정 생성", description = "새로운 환경 설정을 생성합니다")
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
    @Operation(summary = "환경 설정 업데이트", description = "기존 환경 설정을 업데이트합니다")
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
    @Operation(summary = "모든 환경 설정 조회", description = "지정된 애플리케이션/프로필/라벨의 모든 환경 설정을 조회합니다")
    fun retrieveEnvironmentConfigurations(
        @Parameter(description = "애플리케이션 이름") @PathVariable
        application: String,
        @Parameter(description = "프로필 이름") @PathVariable
        profile: String,
        @Parameter(description = "라벨 이름") @PathVariable
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
    @Operation(summary = "특정 환경 설정 조회", description = "지정된 키의 환경 설정을 조회합니다")
    fun retrieveEnvironmentConfiguration(
        @Parameter(description = "애플리케이션 이름") @PathVariable
        application: String,
        @Parameter(description = "프로필 이름") @PathVariable
        profile: String,
        @Parameter(description = "라벨 이름") @PathVariable
        label: String,
        @Parameter(description = "설정 키") @PathVariable
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
    @Operation(summary = "모든 환경 설정 삭제", description = "지정된 애플리케이션/프로필/라벨의 모든 환경 설정을 삭제합니다")
    fun removeEnvironmentConfigurations(
        @Parameter(description = "애플리케이션 이름") @PathVariable
        application: String,
        @Parameter(description = "프로필 이름") @PathVariable
        profile: String,
        @Parameter(description = "라벨 이름") @PathVariable
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
    @Operation(summary = "특정 환경 설정 삭제", description = "지정된 키의 환경 설정을 삭제합니다")
    fun removeEnvironmentConfiguration(
        @Parameter(description = "애플리케이션 이름") @PathVariable
        application: String,
        @Parameter(description = "프로필 이름") @PathVariable
        profile: String,
        @Parameter(description = "라벨 이름") @PathVariable
        label: String,
        @Parameter(description = "설정 키") @PathVariable
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