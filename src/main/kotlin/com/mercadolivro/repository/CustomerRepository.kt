package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<CustomerModel, Int> {

    fun findByNameContaining(name: String, pageable: Pageable): Page<CustomerModel> //busca por nome que contenha um trecho do texto
    fun existsByEmail(email: String): Boolean
}