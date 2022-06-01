package com.example.kotlinbackend.controller.post

import com.example.kotlinbackend.model.http.UserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping() : String{

        return "post-mapping";
    }

    /* Spring 에서 json 데이터와 object 가 왔다 갔다 할떄는
       object mapper 사용 object mapper 의 역할은
       json -> object // object -> json 으로 바꿔주는 라이브러리
     */

    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest) : UserRequest{
       // json -> object
        println("request :" + userRequest)

        // object -> json
        return userRequest

    }
}