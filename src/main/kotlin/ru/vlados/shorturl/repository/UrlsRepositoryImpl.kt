package ru.vlados.shorturl.repository

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import ru.vlados.shorturl.repository.entity.UrlEntity

private const val SQL_INSERT_URL = """
   insert into task_color (hash, originalPath, expiration)
   values (:hash, :originalPath, :expiration)
"""

@Service
class UrlsRepositoryImpl(
    @Qualifier("shortUrlTemplate")
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : UrlsRepository {

    override fun insert(urlEntity: UrlEntity) {
        jdbcTemplate.update(SQL_INSERT_URL, BeanPropertySqlParameterSource(urlEntity))
    }
}
