package com.mercadolivro.validation

import com.mercadolivro.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(var customerService: CustomerService): ConstraintValidator<EmailAvailable, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()) { //se for uma String nula ou vazia, retorna false, pois esse e-mail esta inválido
            return false
        }
        return customerService.emailAvailable(value) //metodo responsável por olhar para o banco de dados e verificar se esse e-mail existe ou não
    }

}
