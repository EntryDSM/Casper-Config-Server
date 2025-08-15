package hs.kr.casper.configserver.domain.user.model

enum class UserRole(val authority: String) {
    CONFIG_ADMIN("CONFIG_ADMIN"),
    CONFIG_READER("CONFIG_READER")
}