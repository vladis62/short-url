package ru.vlados.shorturl.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse

interface ShortUrlApi {

    @Operation(
        description = "Получение основного урла по короткой ссылке",
        method = "GET"
    )
    @Parameters(
        value = [
            Parameter(name = "shortUrl",
                required = true,
                schema = Schema(minLength = 7, maxLength = 7),
                description = "Name of the short url",
                `in` = QUERY),
        ]
    )
//    @RequestBody(
//        description = "Business Data", content = [Content(
//            mediaType = "application/json",
//            schema = Schema(
//                implementation = UCBusinessData::class
//            )
//        )],
//        required = true
//    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Ok",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(
                            implementation = String::class,
                            name = "get-url"
                        )
                    )
                ]
            ),

            ApiResponse(
                responseCode = "400", description = "Bad request",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(
                            implementation = ErrorResponse::class,
                            name = "error-response"
                        )
                    )
                ]
            ),
            ApiResponse(
                responseCode = "500", description = "Internal server error",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(
                            implementation = ErrorResponse::class,
                            name = "error-response"
                        )
                    )
                ]
            )
        ]
    )
    fun getUrl(shortUrl: String? = null): ResponseEntity<String>
}