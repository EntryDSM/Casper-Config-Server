package hs.kr.casper.configserver.domain.env.repository

import hs.kr.casper.configserver.domain.env.model.env.EnvironmentValue
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EnvironmentRepository : JpaRepository<EnvironmentValue, Long>