package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PostPurchaseRequest(

    @field: NotNull
    @field: Positive //numero maior que 0
    @JsonAlias("customer_id") //usa essa notacao porque estamos enviando essas informacoes no snake case e estamos recebendo elas como em camel case
    val customerId: Int,

    @field: NotNull
    @JsonAlias("book_ids")
    val bookIds: Set<Int>

)
