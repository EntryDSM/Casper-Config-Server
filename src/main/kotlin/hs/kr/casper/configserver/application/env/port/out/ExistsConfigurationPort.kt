package hs.kr.casper.configserver.application.env.port.out

interface ExistsConfigurationPort {
    fun existsConfiguration(application: String, profile: String, label: String, key: String): Boolean
}