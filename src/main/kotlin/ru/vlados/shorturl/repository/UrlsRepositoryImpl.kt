package ru.vlados.shorturl.repository

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import ru.vlados.shorturl.repository.entity.UrlEntity
import ru.vlados.shorturl.repository.rowmapper.UrlsRowMapper

private const val SQL_INSERT_URL = """
   insert into urls (hash, original_url, expiration)
   values (:hash, :originalUrl, :expiration)
"""

private const val SQL_GET_BY_HASH = """
    select * from urls
    where hash = :hash
"""

private const val SQL_GET_BY_LONG_URL = """
    select * from urls
    where original_url = :url
"""

private const val SQL_EXISTS_BY_LONG_URL = """
    select exists(select 1 from urls where original_url = :url)
"""

private val log = KotlinLogging.logger {}

@Service
class UrlsRepositoryImpl(
    @Qualifier("shortUrlTemplate")
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val urlsRowMapper: UrlsRowMapper,
) : UrlsRepository {

    override fun save(urlEntity: UrlEntity) {
        jdbcTemplate.update(SQL_INSERT_URL, BeanPropertySqlParameterSource(urlEntity))
    }

    override fun getByHash(hash: String): UrlEntity = runCatching {
        jdbcTemplate.queryForObject(SQL_GET_BY_HASH, mapOf("hash" to hash), urlsRowMapper)!!
    }.getOrElse {
        log.error(it) { "Error get url by hash: $hash" }
        throw IllegalArgumentException(it)
    }

    override fun getByOriginalUrl(url: String): UrlEntity = runCatching {
        jdbcTemplate.queryForObject(SQL_GET_BY_LONG_URL, mapOf("url" to url), urlsRowMapper)!!
    }.getOrElse {
        log.error(it) { "Error get url by originalUrl: $url" }
        throw IllegalArgumentException(it)
    }

    override fun exists(url: String): Boolean = runCatching {
        jdbcTemplate.queryForObject(SQL_EXISTS_BY_LONG_URL, mapOf("url" to url), Boolean::class.java)!!
    }.getOrDefault(false)
}
