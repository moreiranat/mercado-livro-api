package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService (
    val customerRepository: CustomerRepository,
    val bookService: BookService
    ) {

    fun getAll(name: String?, pageable: Pageable): Page<CustomerModel> { //String? --> o nome pode vir nulo
        name?.let {
            return customerRepository.findByNameContaining(it, pageable)
        }
        return customerRepository.findAll(pageable)
    }

    fun createCustomer(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
    }

    fun update(customer: CustomerModel) {

        if(!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) { //quando deletar o customer, deletar tambem os livros dele
        val customer = findById(id) //para pegar o customer
        bookService.deleteByCustomer(customer) //chama o delete do service

        customer.status = CustomerStatus.INATIVO //ao deletar o customer, o status dele vai para inativo

        customerRepository.save(customer)
    }
}