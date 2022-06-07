package com.example.kotlinbackend.controller.delete

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam(value = "name") name : String,
        @RequestParam(value = "age") age : Int
    ) : String{

        println(name)
        println(age)

        return name + " " + age
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
        @PathVariable(name = "name") _name: String,
        @PathVariable(name = "age") _age: Int
    ) : String{

        return _name + "  " + _age;
    }

    
}