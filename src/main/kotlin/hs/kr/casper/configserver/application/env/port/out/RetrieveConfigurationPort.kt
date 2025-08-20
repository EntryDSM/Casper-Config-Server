package hs.kr.casper.configserver.application.env.port.out

interface RetrieveConfigurationPort {
    fun retrieveConfiguration(application: String, profile: String, label: String, key: String): Map<String, String>
    fun retrieveConfigurations(application: String, profile: String, label: String): Map<String, String>
}