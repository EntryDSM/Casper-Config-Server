package hs.kr.casper.configserver.application.env.port.`in`

import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentOperationResponse
import hs.kr.casper.configserver.adapter.`in`.env.dto.response.EnvironmentValueResponse

interface SingleEnvironmentValueUseCase {
    fun retrieveValue(application: String, profile: String, label: String, key: String): EnvironmentValueResponse

    fun storeValue(application: String, profile: String, label: String, key: String, value: String): EnvironmentOperationResponse

    fun removeValue(application: String, profile: String, label: String, key: String): EnvironmentOperationResponse
}