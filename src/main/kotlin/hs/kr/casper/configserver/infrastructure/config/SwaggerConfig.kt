package hs.kr.casper.configserver.infrastructure.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Casper Config Server API")
                    .description("환경 설정 관리를 위한 Config Server API")
                    .version("1.0.0")
            )
    }
}