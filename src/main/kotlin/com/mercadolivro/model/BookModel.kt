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

    @ManyToOne //muitos livros para um usuario
    @JoinColumn(name = "customer_id") //customer_id é referente ao identificador chave primaria que esta em CustomerModel
    var customer: CustomerModel? = null
) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null //status é nullable e o valor padrao dele esta como nulo
        set(value) { //value = novo valor do atributo. Sobrescreve o metodo)
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw Exception("Não é possível alterar um livro com status ${field}.name")
            }
            field = value //sobrescreve o novo valor
        }

    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerModel? = null,
                status: BookStatus?): this(id, name, price, customer) {
        this.status = status
    }
}