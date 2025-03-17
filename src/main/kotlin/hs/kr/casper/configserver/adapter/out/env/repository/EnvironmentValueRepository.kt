package hs.kr.casper.configserver.adapter.out.env.repository

import hs.kr.casper.configserver.domain.env.model.EnvironmentValue
import org.springframework.data.jpa.repository.JpaRepository

interface EnvironmentValueRepository : JpaRepository<EnvironmentValue, Long> {
    fun findByApplicationAndProfileAndLabel(application: String, profile: String, label: String): List<EnvironmentValue>
}