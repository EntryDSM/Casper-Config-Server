package hs.kr.casper.configserver.adapter.`in`.env

import hs.kr.casper.configserver.adapter.`in`.env.dto.request.EnvironmentValuesRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/env")
class EnvironmentValueWebAdapter(

) {
    @PostMapping
    fun createEnvironmentValues(
        @RequestBody
        environmentValuesRequest: EnvironmentValuesRequest
    ) : EnvironmentValuesRequest{
        return environmentValuesRequest
    }
}