package hs.kr.casper.configserver.adapter.out.persistence.env.repository

import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EnvironmentConfigurationRepository : JpaRepository<EnvironmentConfigurationJpaEntity, Long> {
    fun deleteByApplicationAndProfileAndLabelAndKey(application: String, profile: String, label: String, key: String)

    fun existsAllByApplicationAndProfileAndLabelAndKey(application: String, profile: String, label: String, key: String): Boolean

    fun findByApplicationAndProfileAndLabel(application: String, profile: String, label: String): List<EnvironmentConfigurationJpaEntity>

    fun findByApplicationAndProfileAndLabelAndKey(application: String, profile: String, label: String, key: String): List<EnvironmentConfigurationJpaEntity>
    
    fun findByApplicationAndProfileAndLabelAndCreatedBy(application: String, profile: String, label: String, createdBy: UUID): List<EnvironmentConfigurationJpaEntity>
    
    fun findByApplicationAndProfileAndLabelAndKeyAndCreatedBy(application: String, profile: String, label: String, key: String, createdBy: UUID): List<EnvironmentConfigurationJpaEntity>
    
    fun deleteByApplicationAndProfileAndLabelAndCreatedBy(application: String, profile: String, label: String, createdBy: UUID)
    
    fun deleteByApplicationAndProfileAndLabelAndKeyAndCreatedBy(application: String, profile: String, label: String, key: String, createdBy: UUID)
}