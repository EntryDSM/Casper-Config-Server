package hs.kr.casper.configserver.infrastructure.exception

open class EntryUtilsException(
    override val message: String
): RuntimeException(message) {
    companion object {
        fun keyNotFound(message: String) = EntryUtilsException(message)
        fun saltNotFound(message: String) = EntryUtilsException(message)
    }
}