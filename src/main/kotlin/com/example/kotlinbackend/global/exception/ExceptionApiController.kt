package com.example.kotlinbackend.global.exception

import com.example.kotlinbackend.model.http.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.ConstraintViolationException
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import com.example.kotlinbackend.model.http.Error
import com.example.kotlinbackend.model.http.UserRequest
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(){
        val list = mutableListOf<String>()
        val temp = list[0]
    }

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min = 2, max = 6)
        @RequestParam name: String,

        @Min(10)
        @RequestParam age: Int,
    ): String {
        println(name)
        println(age)
        return name + " " +age
    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)

        return userRequest

    }

    @ExceptionHandler(value =  [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach { errorObject ->
            val error = Error().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }
            errors.add(error)
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString() // 현재 요청한 주소
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.status(400).body(errorResponse)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(e : ConstraintViolationException, request : HttpServletRequest) : ResponseEntity<ErrorResponse>{ // HttpServletRequest -> 스프링에서 자동으로 현재 리퀘스트를 찾아줌.
        // 1.에러 분석
        val errors = mutableListOf<Error>()

        e.constraintViolations.forEach { // constraintViolations 안에서 어떤 에러를 가지고 있는지 가져올 수 있다.
            val error = Error().apply {
                this.field = it.propertyPath.last().name
                this.message = it.message
                this.value = it.invalidValue // 유효하지 않은 값.

            }
            errors.add(error)
        }

        // 2. Error Response
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생하였습니다."
            this.path = request.requestURI.toString() // 현재 요청한 주소
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        // 3. ResponseEntity
        return ResponseEntity.status(400).body(errorResponse)
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class]) // 컨트롤러 내부에 @ExceptionHandler가 존재 하면 @RestControllerAdvice를 타지 않음.
    fun indexOutOfBoundsException(e : IndexOutOfBoundsException): ResponseEntity<String> {
        println("타냐?")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}