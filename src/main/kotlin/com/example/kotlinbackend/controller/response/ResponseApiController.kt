package com.example.kotlinbackend.controller.response

import com.example.kotlinbackend.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4xx
    @GetMapping("")
    fun getMapping(@RequestParam age : Int?): ResponseEntity<String> { // required = true -> 반드시 필요한 값.(default true 이므로 선언 안해줘도 됨.)

     /*   // 1. age == null -> 400 error
        if(age == null){
            //return ResponseEntity.badRequest().body("fail")
            return  ResponseEntity.status(400).body("age 값이 누락 되었습니다.")
        }

        // 2. age < 20 --> 20보다 작은경우 400 error
        if(age <= 20){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 20보다 커야합니다.")
        }
        return ResponseEntity.ok("OK")
        */
        // 위의 코드를 조금더 코틀린 스럽게

        return age?.let {
            // age != null
            if(it <= 20){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 20보다 커야합니다.")
            }
            ResponseEntity.ok("OK")

        }?: kotlin.run {
            // age == null
            return  ResponseEntity.status(400).body("age 값이 누락 되었습니다.")
        }
    }

    // 2. post 200

    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(userRequest)
    }

    // 3. put 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 1. 가설 : 기존 데이터가 없어서 새로 생성함 -> 201 return
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest) // CREATED 컨트롤(커맨드) 키를 눌러서 들어가면 상태값을 알 수 있다.

    }

    // 4. delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id : Long): ResponseEntity<Any> {

        return ResponseEntity.status(500).body("Server Error")
    }
}