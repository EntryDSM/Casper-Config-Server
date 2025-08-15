package hs.kr.casper.configserver.application.user.service

import hs.kr.casper.configserver.application.user.port.`in`.AuthenticateUserUseCase
import hs.kr.casper.configserver.application.user.port.out.FindUserPort
import hs.kr.casper.configserver.application.user.port.out.SaveUserPort
import hs.kr.casper.configserver.domain.user.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class AuthenticationService(
    private val findUserPort: FindUserPort,
    private val saveUserPort: SaveUserPort,
    private val passwordEncoder: PasswordEncoder
) : AuthenticateUserUseCase {
    
    override fun authenticate(username: String, password: String): User? {
        val user = findUserPort.findByUsername(username) ?: return null
        
        if (!user.enabled) {
            return null
        }
        
        return if (passwordEncoder.matches(password, user.password)) {
            user
        } else {
            null
        }
    }
    
    override fun updateLastLogin(user: User) {
        val updatedUser = user.copy(lastLoginAt = LocalDateTime.now())
        saveUserPort.save(updatedUser)
    }
}