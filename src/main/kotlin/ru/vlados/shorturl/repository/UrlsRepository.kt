package ru.vlados.shorturl.repository

import ru.vlados.shorturl.repository.entity.UrlEntity

interface UrlsRepository {

    fun insert(urlEntity: UrlEntity)
}