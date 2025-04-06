package hs.kr.casper.configserver.infrastructure.exception

import org.springframework.http.HttpStatus

open class EntryHttpException(
    val status: HttpStatus,
    override val message: String
): RuntimeException(message) {
    companion object {
        fun badRequest(message: String) = EntryHttpException(HttpStatus.BAD_REQUEST, message)
        fun unauthorized(message: String) = EntryHttpException(HttpStatus.UNAUTHORIZED, message)
        fun forbidden(message: String) = EntryHttpException(HttpStatus.FORBIDDEN, message)
        fun notFound(message: String) = EntryHttpException(HttpStatus.NOT_FOUND, message)
        fun conflict(message: String) = EntryHttpException(HttpStatus.CONFLICT, message)
        fun internalServerError(message: String) = EntryHttpException(HttpStatus.INTERNAL_SERVER_ERROR, message)
    }
}