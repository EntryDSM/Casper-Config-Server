package hs.kr.casper.configserver.infrastructure.persistence.env

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity(name = "tbl_environment_configuration")
data class EnvironmentConfigurationJpaEntity(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
        name = "uuid2",
        strategy = "org.hibernate.id.UUIDGenerator")
    @Column(
        name = "id",
        nullable = false,
        updatable = false,
        columnDefinition = "BINARY(16)"
    )
    val id: UUID = UUID.randomUUID(),

    @Column(
        name = "application",
        nullable = false,
        columnDefinition = "VARCHAR(20)"
    )
    val application: String,

    @Column(
        name = "profile",
        nullable = false,
        columnDefinition = "VARCHAR(24)"
    )
    val profile: String,

    @Column(
        name = "label",
        nullable = false,
        columnDefinition = "VARCHAR(100)"
    )
    val label: String,
    @Column(
        name = "prop_key",
        nullable = false,
        columnDefinition = "VARCHAR(100)"
    )
    val key: String,

    @Column(
        name = "prop_value",
        nullable = false,
        columnDefinition = "TEXT"
    )
    val value: String,
)