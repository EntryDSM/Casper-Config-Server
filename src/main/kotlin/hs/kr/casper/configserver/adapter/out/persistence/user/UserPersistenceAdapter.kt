package hs.kr.casper.configserver.adapter.out.persistence.user

import hs.kr.casper.configserver.adapter.out.persistence.user.repository.UserRepository
import hs.kr.casper.configserver.application.user.port.out.FindUserPort
import hs.kr.casper.configserver.application.user.port.out.SaveUserPort
import hs.kr.casper.configserver.domain.user.model.User
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : FindUserPort, SaveUserPort {
    
    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)?.let { userMapper.toDomain(it) }
    }
    
    override fun findById(id: UUID): User? {
        return userRepository.findById(id).orElse(null)?.let { userMapper.toDomain(it) }
    }
    
    override fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }
    
    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
    
    override fun save(user: User): User {
        val entity = userMapper.toEntity(user)
        val savedEntity = userRepository.save(entity)
        return userMapper.toDomain(savedEntity)
    }
}