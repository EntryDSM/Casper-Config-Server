package hs.kr.casper.configserver.adapter.out.env.repository

import hs.kr.casper.configserver.domain.env.model.EnvironmentConfiguration
import org.springframework.data.jpa.repository.JpaRepository

interface EnvironmentConfigurationRepository : JpaRepository<EnvironmentConfiguration, Long> {
    fun findByApplicationAndProfileAndLabel(application: String, profile: String, label: String): List<EnvironmentConfiguration>
}