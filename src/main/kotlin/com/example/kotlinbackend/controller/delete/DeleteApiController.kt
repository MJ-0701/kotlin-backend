package com.example.kotlinbackend.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated // 유효성을 검증하기 위해서는 bean으로 생성 되어야 하지만 밑에 파라미터 들은 빈이 아닌 변수이기 때문에 해당 @Validated 어노테이션을 통해 하위에 있는
// Validation annotation 들을 동작하게 해준다. -> 하위 페이지 전체를 검증
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam(value = "name") name : String,

        @NotNull(message = "age 값은 필수값 입니다.")
        @Min(value = 20, message = "20이상이어야 합니다.")
        @RequestParam(value = "age") age : Int
    ) : String{

        println(name)
        println(age)

        return name + " " + age
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
        @Size(min = 2, max = 5, message = "name의 길이는 2~5 사이여야 합니다.")
        @NotNull
        @PathVariable(name = "name") _name: String,

        @NotNull(message = "age 값은 필수값 입니다.")
        @Min(value = 20, message = "20이상이어야 합니다.")
        @PathVariable(name = "age") _age: Int
    ) : String{

        return _name + "  " + _age;
    }

    
}