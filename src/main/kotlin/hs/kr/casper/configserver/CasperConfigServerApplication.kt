package hs.kr.casper.configserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CasperConfigServerApplication

fun main(args: Array<String>) {
    runApplication<CasperConfigServerApplication>(*args)
}
