package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>() //lista mutavel do tipo CustomerModel

    fun getAll(name: String?): List<CustomerModel> { //String? --> o nome pode vir nulo
        name?.let {
            return customers.filter { it.name.contains(name, true) } //se o nome nao vier nulo, executa o bloco de código dentro do let, contendo o trecho do nome o ignorando letras maiusculas ou minusculas
        }
        return customers
    }

    fun createCustomer(customer: CustomerModel) {

        val id = if(customers.isEmpty()) {
            1
        } else {
            customers.last().id!!.toInt() + 1
        }.toString() //passa o id para String

        customer.id = id

        customers.add(customer)
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.filter { it.id == id }.first() //vê se o id é igual ao que está sendo passado como parâmetro e retorna ele. Retorna o 1° registro que isso acontecer
    }

    fun update(customer: CustomerModel) {
        customers.filter { it.id == customer.id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}