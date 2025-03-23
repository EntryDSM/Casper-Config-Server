package hs.kr.casper.configserver.infrastructure.error

data class ErrorResponse(
    val status: Int,
    val message: String
)