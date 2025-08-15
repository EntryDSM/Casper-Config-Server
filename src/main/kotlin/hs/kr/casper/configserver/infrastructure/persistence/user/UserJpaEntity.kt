package hs.kr.casper.configserver.infrastructure.persistence.user

import hs.kr.casper.configserver.domain.user.model.UserRole
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "tbl_config_users",
    indexes = [
        Index(name = "idx_username", columnList = "username", unique = true)
    ]
)
class UserJpaEntity(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID,
    
    @Column(length = 50, nullable = false, unique = true)
    val username: String,
    
    @Column(length = 255, nullable = false)
    val password: String,
    
    @Column(length = 100)
    val email: String?,
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    val role: UserRole,
    
    @Column(nullable = false)
    val enabled: Boolean = true,
    
    @CreationTimestamp
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column
    var lastLoginAt: LocalDateTime? = null
)