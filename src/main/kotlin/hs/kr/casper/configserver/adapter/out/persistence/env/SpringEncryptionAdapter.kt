package hs.kr.casper.configserver.adapter.out.persistence.env

import hs.kr.casper.configserver.application.env.port.out.EncryptionPort
import org.springframework.core.env.Environment
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.stereotype.Component

@Component
class SpringEncryptionAdapter(
    private val environment: Environment
) : EncryptionPort {

    private val textEncryptor: TextEncryptor by lazy {
        val key = environment.getProperty("encrypt.key") ?: throw IllegalStateException("No encryption key provided")
        Encryptors.text(key, "deadbeef")
    }

    override fun encrypt(value: String): String {
        return textEncryptor.encrypt(value)
    }

    override fun decrypt(value: String): String {
        return textEncryptor.decrypt(value)
    }

    override fun isEncrypted(value: String): Boolean {
        return value.startsWith("{cipher}")
    }
}