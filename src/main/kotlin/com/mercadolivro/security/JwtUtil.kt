package com.mercadolivro.security

import com.mercadolivro.exception.AuthenticationException
import io.jsonwebtoken.Claims
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

    fun isValidToken(token: String): Boolean {
        //claims: informações contidas no token
        val claims = getClaims(token)
        if (claims.subject == null || claims.expiration == null || Date().after((claims.expiration))) {//subject == null || expiration == null || data da expiração expirou
            return false
        }

        return true
    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(secret!!.toByteArray()).parseClaimsJws(token).body
        } catch (ex : Exception) {
            throw AuthenticationException("Token Inválido", "999")
        }
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }
}