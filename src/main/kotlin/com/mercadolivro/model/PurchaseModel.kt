package com.mercadolivro.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity(name = "purchase")
data class PurchaseModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @ManyToOne //apenas um único customer por realizr várias compras (muitas compras para 1 customer)
    @JoinColumn(name = "customer_id")
    val customer: CustomerModel,

    @ManyToMany //1 compra pode ter varios livros e esse mesmo livro pode está em várias compras
    @JoinTable(name = "purchase_book", //intersecção de compras e livros
        joinColumns = [JoinColumn(name = "purchase_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")])
    val books: List<BookModel>,

    @Column
    val nfe: String? = null, //nota fiscal eletronica

    @Column
    val price: BigDecimal,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
)