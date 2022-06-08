package com.example.kotlinbackend.global.annotation

import com.example.kotlinbackend.global.validator.StringFormatDateTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [StringFormatDateTimeValidator::class])
@Target( // 적용 범위
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME) // 실행 시점
@MustBeDocumented // 코틀린에서는 붙여 줘야함.
annotation class StringFormatDateTime (
    val pattern : String = "yyyy-MM-dd HH:mm:ss",
    val message : String = "시간 형식이 유효하지 않습니다.",
    val groups: Array<KClass<*>> = [], // 어노테이션을 생성할때 디폴트로 이런 값이 들어간다 정도 로 알고이씅면 됨.
    val payload : Array<KClass<out Payload>> = []

)

