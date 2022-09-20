package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null, //status é nullable e o valor padrao dele esta como nulo

    @ManyToOne //muitos livros para um usuario
    @JoinColumn(name = "customer_id") //customer_id é referente ao identificador chave primaria que esta em CustomerModel
    var customer: CustomerModel? = null
)