package hs.kr.casper.configserver.infrastructure.exception

import org.springframework.http.HttpStatus

open class EntryException(
    val code: HttpStatus,
    override val message: String
): RuntimeException(message) {
    companion object {
        fun badRequest(message: String) = EntryException(HttpStatus.BAD_REQUEST, message)
        fun unauthorized(message: String) = EntryException(HttpStatus.UNAUTHORIZED, message)
        fun forbidden(message: String) = EntryException(HttpStatus.FORBIDDEN, message)
        fun notFound(message: String) = EntryException(HttpStatus.NOT_FOUND, message)
        fun conflict(message: String) = EntryException(HttpStatus.CONFLICT, message)
        fun internalServerError(message: String) = EntryException(HttpStatus.INTERNAL_SERVER_ERROR, message)
    }
}