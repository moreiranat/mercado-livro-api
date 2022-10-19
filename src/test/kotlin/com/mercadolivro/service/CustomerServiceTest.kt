package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Role
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class) //vai injetar tudo o que precisa (injeta os mocks do mockK)
class CustomerServiceTest {

    //mocks são classes que não fazem nada de verdade. Não usamos as classes verdadeiras para os testes, usamos mocks,
    //para garantir que as classes estão sendo chamadas

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var bookService: BookService

    @MockK
    private lateinit var bCrypt: BCryptPasswordEncoder

    @InjectMockKs //cria uma instancia, injetando todos os mocks
    private lateinit var customerService: CustomerService //lateinit: variavel que vai ser instanciada depois

    @Test
    fun `should return all customer`() {
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findAll() } returns fakeCustomers //toda vez que chamar o findAll, vai retornar essa lista com os fakeCustomers

        val customers = customerService.getAll(null)

        //verifica se fakeCustomers é igual a customers. expected (o que é esperado): fakeCustomers; retorno do método: customers
        assertEquals(fakeCustomers, customers)
        //para garantir que o customerRepository.findAll foi chamado exatamente 1 vez
        verify(exactly = 1) { customerRepository.findAll() }
        //para garantir que o metodo findByNameContaining não está sendo chamado
        verify(exactly = 0) { customerRepository.findByNameContaining(any())} //se não passar nada não é para esse metodo ser chamado
    }

    @Test
    fun `should return customers when name is informed`() {
        val name = UUID.randomUUID().toString() //gera nomes aleatorios
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findByNameContaining(name) } returns fakeCustomers //toda vez que chamar o findAll, vai retornar essa lista com os fakeCustomers

        val customers = customerService.getAll(name)

        //verifica se fakeCustomers é igual a customers. expected (o que é esperado): fakeCustomers; retorno do método: customers
        assertEquals(fakeCustomers, customers)
        //para garantir que o customerRepository.findAll foi chamado exatamente 1 vez
        verify(exactly = 0) { customerRepository.findAll() }
        //para garantir que o metodo findByNameContaining não está sendo chamado
        verify(exactly = 1) { customerRepository.findByNameContaining(name)} //se não passar nada não é para esse metodo ser chamado
    }

    fun buildCustomer(
        id: Int? = null,
        name: String = "customer name",
        email: String = "${UUID.randomUUID()}@email.com",
        password: String = "password"
    ) = CustomerModel(
        id = id,
        name = name,
        email = email,
        status = CustomerStatus.ATIVO,
        password = password,
        roles = setOf(Role.CUSTOMER)
    )

}