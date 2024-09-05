package ru.vlados.shorturl.service

import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import ru.vlados.shorturl.model.OriginalUrlResponse
import ru.vlados.shorturl.model.ShortUrlResponse
import ru.vlados.shorturl.repository.UrlsRepository
import ru.vlados.shorturl.repository.entity.UrlEntity
import ru.vlados.shorturl.util.URLHasher.generateHash

@Service
class UrlServiceImpl(
    private val urlsRepository: UrlsRepository,
    private val applicationContext: ApplicationContext
) : UrlService {

    override fun getOriginalUrl(hash: String): OriginalUrlResponse {
        val originalUrl = urlsRepository.getByHash(hash).originalUrl
        return OriginalUrlResponse(originalUrl)
    }

    override fun getShortUrl(originalUrl: String): ShortUrlResponse {
        val hash = urlsRepository.getByOriginalUrl(originalUrl).hash
        return buildShortUrl(hash)
    }

    override fun saveUrl(url: String): ShortUrlResponse {
        if (urlsRepository.exists(url)) {
            throw IllegalArgumentException("Url: $url is exists")
        }
        val hash = generateHash(url)
        val urlEntity = UrlEntity(hash, url)
        urlsRepository.save(urlEntity)
        return buildShortUrl(hash)
    }


    private fun buildShortUrl(hash: String) =
        ShortUrlResponse("http://localhost:8080${applicationContext.applicationName}/$hash")
}