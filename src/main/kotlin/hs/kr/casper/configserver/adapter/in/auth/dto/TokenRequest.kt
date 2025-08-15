package hs.kr.casper.configserver.adapter.`in`.auth.dto

data class TokenRequest(
    val username: String,
    val password: String
)