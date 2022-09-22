package com.mercadolivro.controller.response

data class FieldErrorResponse(
    var message: String, //porque deu erro?
    var field: String //qual o campo que deu erro?
)
