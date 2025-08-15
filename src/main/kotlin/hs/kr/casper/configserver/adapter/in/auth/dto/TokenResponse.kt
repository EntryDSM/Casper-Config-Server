package hs.kr.casper.configserver.adapter.`in`.auth.dto

data class TokenResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)