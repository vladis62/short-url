package ru.vlados.shorturl.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity

interface ProxyUrlApi {

    @Operation(
        description = "Редирект на основной урл по короткой ссылке",
        method = "GET"
    )
    @Parameters(
        value = [
            Parameter(name = "hash",
                required = true,
                schema = Schema(minLength = 7, maxLength = 7),
                description = "Хэш короткой ссылки",
                `in` = ParameterIn.QUERY
            ),
        ]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "301",
                description = "Redirected to the long URL",
                headers = [
                    Header(
                        name = "Location",
                        description = "The URL to which the client is redirected",
                        schema = Schema(type = "string")
                    )
                ],
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(
                responseCode = "404",
                description = "URL not found",
                content = [Content(mediaType = "application/json")]
            )
        ]
    )
    fun proxyUrl(hash: String): ResponseEntity<Unit>
}