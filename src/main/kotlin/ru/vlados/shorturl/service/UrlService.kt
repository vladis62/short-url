package ru.vlados.shorturl.service

import ru.vlados.shorturl.model.OriginalUrlResponse
import ru.vlados.shorturl.model.ShortUrlResponse

interface UrlService {

    fun getOriginalUrl(hash: String): OriginalUrlResponse

    fun getShortUrl(originalUrl: String): ShortUrlResponse

    fun saveUrl(url: String): ShortUrlResponse
}