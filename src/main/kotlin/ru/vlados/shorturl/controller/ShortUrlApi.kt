package ru.vlados.shorturl.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import ru.vlados.shorturl.model.OriginalUrlResponse
import ru.vlados.shorturl.model.SaveUrlRequest
import ru.vlados.shorturl.model.ShortUrlResponse

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
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Ok",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(
                            implementation = String::class,
                            name = "get-long-url"
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
    fun getOriginalUrl(hash: String): ResponseEntity<OriginalUrlResponse>

    @Operation(
        description = "Получение короткой ссылки по длинной ссылке",
        method = "GET"
    )
    @Parameters(
        value = [
            Parameter(
                name = "originalUrl",
                required = true,
                description = "Name of the long url",
                `in` = QUERY
            ),
        ]
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Ok",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(
                            implementation = ShortUrlResponse::class,
                            name = "get-short-url"
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
    fun getShortUrl(originalUrl: String): ResponseEntity<ShortUrlResponse>

    @Operation(
        description = "Сохранение новой ссылки",
        method = "POST"
    )
    @RequestBody(
        description = "Business Data", content = [Content(
            mediaType = "application/json",
            schema = Schema(
                implementation = SaveUrlRequest::class
            )
        )],
        required = true
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Created",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(
                            implementation = ShortUrlResponse::class,
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
    fun saveUrl(originalUrlRequest: SaveUrlRequest): ResponseEntity<ShortUrlResponse>
}