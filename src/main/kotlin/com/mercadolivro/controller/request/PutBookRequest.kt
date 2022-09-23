package com.mercadolivro.controller.request

import java.math.BigDecimal

data class PutBookRequest(
    //pode ou não receber o name e o price; pode deixar o valor antigo ou o novo

    var name: String?,
    var price: BigDecimal?
)


