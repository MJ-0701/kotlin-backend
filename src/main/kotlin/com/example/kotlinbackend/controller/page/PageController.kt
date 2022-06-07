package com.example.kotlinbackend.controller.page

import com.example.kotlinbackend.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PageController {

    @GetMapping("/main")
    fun main() : String {

        return "main.html"
    }

    @ResponseBody // 뷰 리졸버에서 json 결과값을 받아야할때 사용하는 어노테이션.
    @GetMapping("/test")
    fun response(): String {
        return "main.html"
    }

    @ResponseBody
    @GetMapping("json-test")
    fun jsonResponse() : UserRequest{
        return UserRequest().apply {
            this.name = "채명정"
        }
    }

}