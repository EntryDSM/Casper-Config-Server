package hs.kr.casper.configserver.infrastructure.encryption

import org.springframework.cloud.config.server.encryption.EncryptionController
import org.springframework.cloud.config.server.encryption.TextEncryptorLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.crypto.encrypt.TextEncryptor

@Configuration
class EncryptionConfig {
    @Bean
    fun textEncryptorLocator(encryptor: TextEncryptor): TextEncryptorLocator {
        return TextEncryptorLocator { _: Map<String, String> -> encryptor }
    }

    @Bean
    @Primary
    fun encryptionController(textEncryptorLocator: TextEncryptorLocator): EncryptionController {
        return EncryptionController(textEncryptorLocator)
    }
}