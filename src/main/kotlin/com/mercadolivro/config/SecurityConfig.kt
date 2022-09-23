package com.mercadolivro.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig() : WebSecurityConfigurerAdapter() {

    private val PUBLIC_MATCHERS = arrayOf<String>()

    //lista com as urls que queremos permitir
    //Todos os endpoints que estiverem aí dentro, o POST deles vai ser público
    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customers"
    )

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeRequests()
            .antMatchers(*PUBLIC_MATCHERS).permitAll()
            .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll() //tá permitindo a requisição POST na url /customers. Podem ter várias requisições liberadas
            .anyRequest().authenticated() //todas as requests que chegarem têm que está autenticadas
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //as requisições que chegarem vão ser independentes
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder  {
       return BCryptPasswordEncoder()
    }
}