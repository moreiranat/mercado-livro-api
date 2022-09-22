package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {

    //range dos erros: de 100 em 100
    ML101("ML-101", "Book [%s] not exists"),
    ML201("ML-201", "Customer [%s] not exists")
}