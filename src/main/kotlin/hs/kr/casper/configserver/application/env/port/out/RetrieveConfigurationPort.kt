package hs.kr.casper.configserver.application.env.port.out

import hs.kr.casper.configserver.domain.env.model.EnvironmentValue

interface RetrieveConfigurationPort {
    fun retrieveConfiguration(application: String, profile: String, label: String, key: String): EnvironmentValue

    fun retrieveConfigurations(application: String, profile: String, label: String): List<EnvironmentValue>
}