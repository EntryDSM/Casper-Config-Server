package hs.kr.casper.configserver.infrastructure.exception

open class Exception(
    val status: Int,
    message: String
): RuntimeException(message) {
    companion object {
        val BAD_REQUEST = Exception(400, "Bad Request")
        val UNAUTHORIZED = Exception(401, "Unauthorized")
        val FORBIDDEN = Exception(403, "Forbidden")
        val NOT_FOUND = Exception(404, "Not Found")
        val CONFLICT = Exception(409, "Conflict")
        val INTERNAL_SERVER_ERROR = Exception(500, "Internal Server Error")
    }
}