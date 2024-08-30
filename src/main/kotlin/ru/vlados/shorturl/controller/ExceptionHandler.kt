package ru.vlados.shorturl.controller

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.vlados.shorturl.model.ErrorMessage
import ru.vlados.shorturl.model.InnerError

private val logger = KotlinLogging.logger {}

@ControllerAdvice
class ExceptionHandler {


    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ErrorMessage> {
        logger.warn(e.message, e)
        return createErrorResponseEntity(e, message = e.message!!)
    }

    private fun createErrorResponseEntity(
        e: Exception,
        httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
        message: String
    ) =
        ResponseEntity
            .status(httpStatus)
            .body(ErrorMessage(message, InnerError(e.message)))
}