package ru.vlados.shorturl.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration {

    @Configuration
    @ConfigurationProperties(prefix = "datasource")
    class ShortUrlJdbcProperties : HikariConfig()

    @Bean(name = ["shortUrlDataSource"])
    @Primary
    fun shortUrlDataSource(properties: ShortUrlJdbcProperties): DataSource = HikariDataSource(properties)

    @Bean("shortUrlTemplate")
    fun shortUrlTemplate(@Qualifier("shortUrlDataSource") ds: DataSource) = NamedParameterJdbcTemplate(ds)

}