package hs.kr.casper.configserver.application.env.port.out

import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity

interface RetrieveConfigurationPort {
    fun retrieveConfiguration(application: String, profile: String, label: String, properties: Map<String, String>): EnvironmentConfigurationJpaEntity
}