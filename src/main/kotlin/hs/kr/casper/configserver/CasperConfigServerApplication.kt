package hs.kr.casper.configserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class CasperConfigServerApplication

fun main(args: Array<String>) {
    runApplication<CasperConfigServerApplication>(*args)
}
