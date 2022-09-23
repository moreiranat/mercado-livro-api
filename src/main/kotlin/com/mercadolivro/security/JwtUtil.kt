package com.mercadolivro.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}") //pega o caminho do application.yml
    private val expiration: Long? = null

    @Value("\${jwt.secret}") //pega o caminho do application.yml
    private val secret: String? = null

    fun generateToken(id: Int): String {
        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(Date(System.currentTimeMillis() + expiration!!)) //pega a data de agora + milisegundos de expirar
            .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
            .compact()
    }
}