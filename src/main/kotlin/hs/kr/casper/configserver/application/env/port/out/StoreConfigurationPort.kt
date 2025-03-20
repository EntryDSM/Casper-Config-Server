package hs.kr.casper.configserver.application.env.port.out

import hs.kr.casper.configserver.domain.env.model.EnvironmentConfiguration
import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity

interface StoreConfigurationPort {
    fun storeConfiguration(configuration: EnvironmentConfiguration): EnvironmentConfigurationJpaEntity
}