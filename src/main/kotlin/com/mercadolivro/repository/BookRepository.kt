package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>

    //Paginacao - 1ª forma de fazer
//    fun findAll(pageable: Pageable): Page<BookModel>

}