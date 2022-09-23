package com.mercadolivro.events.listener

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.service.BookService
import com.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateSoldBookListener(
    private val bookService: BookService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) { //a nossa aplicação, toda vez que disparar um evento será ouvido nesse ponto
        println("Atualizando status dos livros")
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }

}