package ru.vlados.shorturl.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.vlados.shorturl.model.OriginalUrlResponse
import ru.vlados.shorturl.model.SaveUrlRequest
import ru.vlados.shorturl.model.ShortUrlResponse
import ru.vlados.shorturl.service.UrlService

@RestController
@RequestMapping("api/v1")
class ShortUrlController(
    private val urlService: UrlService
): ShortUrlApi {

    @GetMapping("/{hash}")
    override fun getOriginalUrl(@PathVariable hash: String): ResponseEntity<OriginalUrlResponse> {
        val originalUrl = urlService.getOriginalUrl(hash)
        return ResponseEntity(originalUrl, HttpStatus.OK)
    }

    @GetMapping("/long-url")
    override fun getShortUrl(@RequestParam originalUrl: String): ResponseEntity<ShortUrlResponse> {
        val shortUrl = urlService.getShortUrl(originalUrl)
        return ResponseEntity(shortUrl, HttpStatus.OK)
    }

    @PostMapping
    override fun saveUrl(@RequestBody originalUrlRequest: SaveUrlRequest): ResponseEntity<ShortUrlResponse> {
        val shortUrl = urlService.saveUrl(originalUrlRequest.originalUrl)
        return ResponseEntity(shortUrl, HttpStatus.CREATED)
    }
}