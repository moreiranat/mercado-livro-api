package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>() //lista mutavel do tipo CustomerModel

    fun getAll(name: String?): List<CustomerModel> { //String? --> o nome pode vir nulo
        name?.let {
            return customers.filter { it.name.contains(name, true) } //se o nome nao vier nulo, executa o bloco de código dentro do let, contendo o trecho do nome o ignorando letras maiusculas ou minusculas
        }
        return customers
    }

    fun createCustomer(customer: PostCustomerRequest) {

        val id = if(customers.isEmpty()) {
            1
        } else {
            customers.last().id.toInt() + 1
        }.toString() //passa o id para String

        customers.add(CustomerModel(id, customer.name, customer.email)) //adiciona esse novo CustomerModel na lista
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.filter { it.id == id }.first() //vê se o id é igual ao que está sendo passado como parâmetro e retorna ele. Retorna o 1° registro que isso acontecer
    }

    fun update(id: String, customer: PutCustomerRequest) {
        customers.filter { it.id == id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}