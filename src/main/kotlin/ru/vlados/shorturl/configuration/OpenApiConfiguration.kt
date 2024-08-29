package ru.vlados.shorturl.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("Short Url")
                .description("Api создания коротких ссылок")
        )

    @Bean
    fun groupedOpenApi(): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("public")
            .packagesToScan("ru.vlados")
            .build()

}
