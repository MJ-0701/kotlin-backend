package com.example.kotlinbackend.global.advice

import com.example.kotlinbackend.global.controller.exception.ExceptionApiController
import com.example.kotlinbackend.controller.put.PutApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice // @RestController 에서 발생하는 exception 들을 받음. (basePackageClasses = [ExceptionApiController::class]) -> 붙여주면 해당 클래스의 에러만 잡음.
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e : RuntimeException): String {
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e : IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }

}