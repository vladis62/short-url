package ru.vlados.shorturl.repository.rowmapper

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ru.vlados.shorturl.repository.entity.UrlEntity
import java.sql.ResultSet

@Component
class UrlsRowMapper : RowMapper<UrlEntity> {

    override fun mapRow(rs: ResultSet, rowNum: Int): UrlEntity =
        UrlEntity(
            hash = rs.getString("hash"),
            originalUrl = rs.getString("original_url"),
            expiration = rs.getTimestamp("expiration"),
        )
}
