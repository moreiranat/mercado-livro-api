package com.mercadolivro.controller.response

data class ErrorResponse(
    var httpCode: Int, //status HTTP
    var message: String,
    var internalCode: String,
    var errors: List<FieldErrorResponse>?
)
