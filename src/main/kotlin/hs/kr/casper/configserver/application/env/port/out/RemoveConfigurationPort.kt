package hs.kr.casper.configserver.application.env.port.out

import hs.kr.casper.configserver.domain.env.model.EnvironmentValue

interface RemoveConfigurationPort {
    fun removeConfiguration(application: String, profile: String, label: String, key: String): EnvironmentValue

    fun removeConfigurations(application: String, profile: String, label: String): List<EnvironmentValue>
}