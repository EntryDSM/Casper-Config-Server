package hs.kr.casper.configserver.infrastructure.config

import hs.kr.casper.configserver.adapter.out.persistence.user.repository.UserRepository
import hs.kr.casper.configserver.domain.user.model.UserRole
import hs.kr.casper.configserver.infrastructure.persistence.user.UserJpaEntity
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {
    
    override fun run(vararg args: String?) {
        createDefaultAdminUser()
    }
    
    private fun createDefaultAdminUser() {
        val adminUsername = "admin"
        
        if (!userRepository.existsByUsername(adminUsername)) {
            val adminUser = UserJpaEntity(
                id = UUID.randomUUID(),
                username = adminUsername,
                password = passwordEncoder.encode("config-admin-2024"),
                email = "admin@casper.kr",
                role = UserRole.CONFIG_ADMIN,
                enabled = true,
                createdAt = LocalDateTime.now(),
                lastLoginAt = null
            )
            
            userRepository.save(adminUser)
            println("Default admin user created: $adminUsername")
        } else {
            println("Admin user already exists: $adminUsername")
        }
    }
}