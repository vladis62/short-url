package ru.vlados.shorturl.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vlados.shorturl.service.UrlService
import java.net.URI


@RestController
@RequestMapping
class ProxyUrlController(
    private val urlService: UrlService
): ProxyUrlApi {

    @GetMapping("/{hash}")
    override fun proxyUrl(@PathVariable hash: String): ResponseEntity<Unit> {
        val originalUrl = urlService.getOriginalUrl(hash)
        val headers = HttpHeaders()
        headers.location = URI.create(originalUrl.url)
        return ResponseEntity(headers, HttpStatus.FOUND)
    }
}