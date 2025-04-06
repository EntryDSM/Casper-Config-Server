package hs.kr.casper.configserver.adapter.out.persistence.env

import hs.kr.casper.configserver.application.env.port.out.EncryptionPort
import hs.kr.casper.configserver.infrastructure.error.message.ErrorMessages
import hs.kr.casper.configserver.infrastructure.exception.EntryUtilsException
import org.springframework.core.env.Environment
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.stereotype.Component

@Component
class SpringEncryptionAdapter(
    private val environment: Environment
) : EncryptionPort {

    companion object {
        private const val CIPHER_PREFIX = "{cipher}"
        private const val ENCRYPT_KEY_PROPERTY = "encrypt.key"
        private const val ENCRYPT_SALT_PROPERTY = "encrypt.salt"
    }

    private val textEncryptor: TextEncryptor by lazy {
        val key = environment.getProperty(ENCRYPT_KEY_PROPERTY)
            ?: throw EntryUtilsException.keyNotFound(ErrorMessages.ENTRY_KEY_NOT_FOUND)

        val salt = environment.getProperty(ENCRYPT_SALT_PROPERTY)
            ?: throw EntryUtilsException.saltNotFound(ErrorMessages.ENTRY_SALT_NOT_FOUND)

        Encryptors.text(key, salt)
    }

    override fun encrypt(value: String): String {
        return "$CIPHER_PREFIX${textEncryptor.encrypt(value)}"
    }

    override fun decrypt(value: String): String {
        if (!isEncrypted(value)) {
            return value
        }

        val encryptedValue = value.substring(CIPHER_PREFIX.length)
        return textEncryptor.decrypt(encryptedValue)
    }

    override fun isEncrypted(value: String): Boolean {
        return value.startsWith(CIPHER_PREFIX)
    }
}