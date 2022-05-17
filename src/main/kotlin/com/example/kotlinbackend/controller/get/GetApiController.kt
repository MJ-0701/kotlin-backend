package com.example.kotlinbackend.controller.get

import com.example.kotlinbackend.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping(path = ["/hello", "/index"]) // path = [] 를 사용하여 주소를 지정해주면 여러가지 주소를 사용가능 하다.
    fun hello() : String{
        return "hello"
    }

    @GetMapping("/get-mapping/path-variable/{name}")
    fun pathVariable(@PathVariable name : String) : String{

        return name;
    }

    @GetMapping("/get-mapping/query-param")
    fun queryParam(@RequestParam name : String, @RequestParam age : Int) : String{

        return name + " " + age;
    }

    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest) : UserRequest{ // 쿼리파라미터가 많을경우 파라미터를 객체로 받는다.
        // 객체로 받은경우 주소를 파싱할 수 없는 경우(ex : phone-number) 는 받을수 없다.

        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map : Map<String, Any>): Map<String, Any> {
        // map 으로 받을경우 -을 사용하여 받을 수 있다.

        return map
    }


}