package ru.vlados.shorturl.repository.entity

import java.sql.Timestamp

data class UrlEntity(
    val hash: String,
    val originalUrl: String,
    val expiration: Timestamp? = null
)
