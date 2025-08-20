package hs.kr.casper.configserver.adapter.out.persistence.env.repository

import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EnvironmentConfigurationRepository : JpaRepository<EnvironmentConfigurationJpaEntity, Long> {
    fun deleteByApplicationAndProfileAndLabelAndKey(application: String, profile: String, label: String, key: String)

    fun existsAllByApplicationAndProfileAndLabelAndKey(application: String, profile: String, label: String, key: String): Boolean

    fun findByApplicationAndProfileAndLabel(application: String, profile: String, label: String): List<EnvironmentConfigurationJpaEntity>

    fun findByApplicationAndProfileAndLabelAndKey(application: String, profile: String, label: String, key: String): List<EnvironmentConfigurationJpaEntity>
}