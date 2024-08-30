package ru.vlados.shorturl.repository

import ru.vlados.shorturl.repository.entity.UrlEntity

interface UrlsRepository {

    fun save(urlEntity: UrlEntity)

    fun getByHash(hash: String): UrlEntity

    fun getByOriginalUrl(url: String): UrlEntity

    fun exists(url: String): Boolean
}