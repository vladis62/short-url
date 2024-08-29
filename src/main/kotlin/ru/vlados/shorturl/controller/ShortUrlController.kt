package ru.vlados.shorturl.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/handle")
class ShortUrlController: ShortUrlApi {

    @GetMapping
    override fun getUrl(shortUrl: String?): ResponseEntity<String> {
        val url = "longUrl"
        return ResponseEntity(url, HttpStatus.CREATED)
    }
}