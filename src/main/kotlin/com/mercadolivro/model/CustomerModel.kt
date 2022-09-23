package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Role
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null, //o id pode ser nulo

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column
    val password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER) //FetchType.EAGER: toda vez que eu for bucar um customer, por ex, toda vez que der um findById, findByEmail, também queremos essas roles. Entao toda vez que for bucar, também busca as informações na tabela de Profile, de Customer Role
    @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
    var roles: Set<Role> = setOf() //Set porque é uma lista que não recebe valores iguais, nunca vai precisar de valores iguais nesse caso
)