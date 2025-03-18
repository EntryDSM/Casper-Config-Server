package hs.kr.casper.configserver.adapter.out.persistence.env.repository

import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EnvironmentConfigurationRepository : JpaRepository<EnvironmentConfigurationJpaEntity, Long> {

}