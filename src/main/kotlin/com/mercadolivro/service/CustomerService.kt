package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.enums.Profile
import com.mercadolivro.exception.NotFoundException
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

    fun create(customer: CustomerModel) {
        val customerCopy = customer.copy(
            roles = setOf(Profile.CUSTOMER) //quem tentar se cadastrar na aplicação é um customer comum
        )
        customerRepository.save(customerCopy)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) }
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

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email) //vai verificar se o e-mail existe, se existir vai retornar false. Essa função verifica se o e-mail esta livre para ser usado por isso coloca negação !, para retornar true
    }
}