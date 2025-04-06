package hs.kr.casper.configserver.application.env.port.out

interface EncryptionPort {
    fun encrypt(value: String): String
    fun decrypt(value: String): String
    fun isEncrypted(value: String): Boolean
}