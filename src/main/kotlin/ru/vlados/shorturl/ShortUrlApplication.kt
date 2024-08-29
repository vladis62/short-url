package ru.vlados.shorturl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication(
	exclude = [
		DataSourceAutoConfiguration::class,
	]
)
class ShortUrlApplication

fun main(args: Array<String>) {
	SpringApplicationBuilder(ShortUrlApplication::class.java)
//		.bannerMode(Banner.Mode.OFF)
		.run(*args)
}
