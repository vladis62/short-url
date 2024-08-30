package ru.vlados.shorturl.model

data class ErrorMessage(
    val message: String,
    val innerError: InnerError? = null
)

data class InnerError(
    val message: String? = null,
)