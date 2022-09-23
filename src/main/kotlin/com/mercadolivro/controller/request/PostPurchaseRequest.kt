package com.mercadolivro.controller.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PostPurchaseRequest(

    @field: NotNull
    @field: Positive //numero maior que 0
    val customerId: Int,

    @field: NotNull
    val bookIds: Set<Int>

)
