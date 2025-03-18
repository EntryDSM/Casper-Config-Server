package hs.kr.casper.configserver.application.env.port.out

import hs.kr.casper.configserver.domain.env.model.EnvironmentValue

interface StoreConfigurationPort {
    fun storeConfiguration(application: String, profile: String, label: String, key: String, value: String): EnvironmentValue

    fun storeConfigurations(application: String, profile: String, label: String, configurations: Map<String, String>): List<EnvironmentValue>
}