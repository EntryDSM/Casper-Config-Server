package hs.kr.casper.configserver.infrastructure.error.handler

import hs.kr.casper.configserver.infrastructure.error.ErrorResponse
import hs.kr.casper.configserver.infrastructure.error.message.ErrorMessages
import hs.kr.casper.configserver.infrastructure.exception.EntryException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(EntryException::class)
    fun handleEntryException(e: EntryException, req: HttpServletRequest): ResponseEntity<ErrorResponse> {
        logger.error("[EntryException] STATUS: ${e.code}, MESSAGE: ${e.message}, PATH: ${req.requestURI}")
        return ResponseEntity.status(e.code).body(
            ErrorResponse(
                status = e.code,
                error = e.message,
                path = req.requestURI
            )
        )
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFoundException(e: NoHandlerFoundException, req: HttpServletRequest): ResponseEntity<ErrorResponse> {
        logger.error("[404 Not Found] STATUS: ${HttpStatus.NOT_FOUND}, MESSAGE: ${e.message}, PATH: ${req.requestURI}")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorResponse(
                status = HttpStatus.NOT_FOUND,
                error = ErrorMessages.ENTRY_NOT_FOUND,
                path = req.requestURI
            )
        )
    }

    @ExceptionHandler(value = [
        HttpMessageNotReadableException::class,
        MethodArgumentNotValidException::class
    ])
    fun handleBadRequestException(e: Exception, req: HttpServletRequest): ResponseEntity<ErrorResponse> {
        logger.error("[400 Bad Request] TYPE: ${e.javaClass.simpleName}, STATUS: ${HttpStatus.BAD_REQUEST}, MESSAGE: ${e.message}, PATH: ${req.requestURI}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                status = HttpStatus.BAD_REQUEST,
                error = ErrorMessages.ENTRY_INVALID_REQUEST,
                path = req.requestURI
            )
        )
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(e: ResponseStatusException, req: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.valueOf(e.statusCode.value())
        logger.error("[ResponseStatusException ${status.value()}] STATUS: $status, REASON: ${e.reason}, PATH: ${req.requestURI}")
        return ResponseEntity.status(status).body(
            ErrorResponse(
                status = status,
                error = e.reason ?: ErrorMessages.ENTRY_INTERNAL_SERVER_ERROR,
                path = req.requestURI
            )
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception, req: HttpServletRequest): ResponseEntity<ErrorResponse> {
        logger.error("[500 Internal Server Error] TYPE: ${e.javaClass.simpleName}, STATUS: ${HttpStatus.INTERNAL_SERVER_ERROR}, MESSAGE: ${e.message}, PATH: ${req.requestURI}")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR,
                error = ErrorMessages.ENTRY_INTERNAL_SERVER_ERROR,
                path = req.requestURI
            )
        )
    }
}