package ru.vlados.shorturl.util

import java.math.BigInteger
import java.security.MessageDigest

object URLHasher {

    private const val BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val messageDigest = md.digest(input.toByteArray())
        val number = BigInteger(1, messageDigest)
        var hashText = number.toString(16)
        while (hashText.length < 8) {
            hashText = "0$hashText"
        }
        return hashText
    }

    private fun base62Encode(hash: String): String {
        var number = BigInteger(hash, 16)
        val base62 = StringBuilder()
        while (number > BigInteger.ZERO) {
            base62.insert(0, BASE62[number.mod(BigInteger.valueOf(62)).toInt()])
            number = number.divide(BigInteger.valueOf(62))
        }
        return base62.toString()
    }

    fun generateHash(originalUrl: String): String {
        val md5Hash = md5(originalUrl)
        val base62Hash = base62Encode(md5Hash)
        return if (base62Hash.length > 7) base62Hash.substring(0, 7) else base62Hash
    }
}