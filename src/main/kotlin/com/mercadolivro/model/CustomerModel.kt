package com.mercadolivro.model

data class CustomerModel(
    var id: String? = null, //o id pode ser nulo
    var name: String,
    var email: String
)