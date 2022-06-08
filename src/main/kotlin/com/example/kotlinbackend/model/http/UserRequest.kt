package com.example.kotlinbackend.model.http

import com.example.kotlinbackend.global.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size


//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) // 해당 클래스를 snake-case 로 지정.
data class UserRequest(


    @field:NotEmpty
    @field:Size(min = 2, max = 5) // 코틀린 데이터 클래스에서는 자바와는 다르게 바로 어노테이션을 붙이는게 아니라 필드 어노테이션을 통해 붙인다.
    var name : String? = null,

    @field:PositiveOrZero // 0이상의 수를 검증(양수)
    var age : Int? = null,

    @field:Email // email 양식
    var email : String? = null,

    @field:NotBlank
    var address : String? = null,

    // @JsonProperty("phone_number") // json 의 naming-convention 은 sanke_case -> 필드가 많아지면 일일이 지정하기 힘들다.
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") // 정규식 검증
    var phoneNumber : String? = null, // 자바/코틀린의 naming-convention 은 camelCase

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:Ss", message = "패턴이 올바르지 않습니다.")
    var createdAt : String? = null // yyyy-MM-dd HH:mm:ss ex) 2020-10-02 13:00:00
    )
{
    // 기존에 스프링부트 밸리데이션에 필요한 내용이 없다면 이런식으로 메소드를 만들어서 사용 할 수 있다.
//    @AssertTrue(message = "생성일자의 패턴은 yyy-MM-dd HH:mm:ss 여야 합니다.") // 메소드는 field 가 아니므로 @field 를 붙이지 않음.
//    private fun isValidCreatedAt() : Boolean{
//        return try {
//            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            true
//        }catch (e : Exception){
//            false
//        }
//    }

}