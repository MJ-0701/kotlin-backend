package com.example.kotlinbackend.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming


//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) // 해당 클래스를 snake-case 로 지정.
data class UserRequest(
    var name : String? = null,
    var age : Int? = null,
    var email : String? = null,
    var address : String? = null,

    // @JsonProperty("phone_number") // json 의 naming-convention 은 sanke_case -> 필드가 많아지면 일일이 지정하기 힘들다.
    var phoneNumber : String? = null // 자바/코틀린의 naming-convention 은 camelCase
    )