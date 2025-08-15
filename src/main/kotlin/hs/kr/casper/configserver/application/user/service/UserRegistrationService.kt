package hs.kr.casper.configserver.application.user.service

import hs.kr.casper.configserver.application.user.port.`in`.RegisterUserUseCase
import hs.kr.casper.configserver.application.user.port.out.FindUserPort
import hs.kr.casper.configserver.application.user.port.out.SaveUserPort
import hs.kr.casper.configserver.domain.user.model.User
import hs.kr.casper.configserver.domain.user.model.UserRole
import hs.kr.casper.configserver.infrastructure.exception.EntryHttpException
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional
class UserRegistrationService(
    private val findUserPort: FindUserPort,
    private val saveUserPort: SaveUserPort,
    private val passwordEncoder: PasswordEncoder
) : RegisterUserUseCase {
    
    override fun registerUser(
        username: String,
        password: String,
        email: String?,
        role: UserRole
    ): User {
        if (findUserPort.existsByUsername(username)) {
            throw EntryHttpException(HttpStatus.CONFLICT, "이미 존재하는 사용자명입니다.")
        }
        if (email != null && findUserPort.existsByEmail(email)) {
            throw EntryHttpException(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.")
        }
        val encodedPassword = passwordEncoder.encode(password)
        
        val newUser = User(
            id = UUID.randomUUID(),
            username = username,
            password = encodedPassword,
            email = email,
            role = role,
            enabled = true,
            createdAt = LocalDateTime.now(),
            lastLoginAt = null
        )
        
        return saveUserPort.save(newUser)
    }
}