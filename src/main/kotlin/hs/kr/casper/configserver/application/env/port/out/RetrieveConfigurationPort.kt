package hs.kr.casper.configserver.application.env.port.out

import java.util.*

interface RetrieveConfigurationPort {
    fun retrieveConfiguration(application: String, profile: String, label: String, key: String): Map<String, String>
    fun retrieveConfigurations(application: String, profile: String, label: String): Map<String, String>
    fun retrieveConfigurationByUser(application: String, profile: String, label: String, key: String, userId: UUID): Map<String, String>
    fun retrieveConfigurationsByUser(application: String, profile: String, label: String, userId: UUID): Map<String, String>
}