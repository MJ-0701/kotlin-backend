package com.example.kotlinbackend.controller.put

import com.example.kotlinbackend.model.http.Result
import com.example.kotlinbackend.model.http.UserRequest
import com.example.kotlinbackend.model.http.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping() : String{

        return "put-mapping";
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"]) // 예전에 사용하던 방식 알아만 둘것.
    fun requestMapping(){

    }

    @PutMapping(path = ["/put-mapping/object"]) // path = [] 이렇게 지정하면 여러 주소를 사용 할 수 있다.
    fun putMappingObject(@RequestBody userRequest: UserRequest) : UserResponse{

        // 0. Response
       return  UserResponse().apply { // todo :: 코틀린 apply 패턴 알아볼것.
            // 1. result
            this.result =  Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2. description
            this.description = "~~~~~~~~"
        }.apply {
            // 3. user MutableList
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)

            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.address = "경기도 안산시"
                this.email = "a@gmail.com"
                this.phoneNumber = "010-1111-2222"
            })

            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 20
                this.address = "경기도 안산시"
                this.email = "b8@gmail.com"
                this.phoneNumber = "010-2222-2222"
            })
            this.userRequest = userList
        }

    }
}