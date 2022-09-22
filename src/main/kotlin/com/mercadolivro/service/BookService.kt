package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
    ){

    fun createBook(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow{ NotFoundException("Book [${id}] not exists", "ML-0001") } //tenta buscar por id, caso o id nao exista ele estoura um erro
    }

    fun delete(id: Int) { //nunca vai apagar os livros da base de dados (status cancelado)
        val book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    //delecao de livros atraves da delecao de customer
    fun deleteByCustomer(customer: CustomerModel) { //quando o customer for deletado o livro passa para o status deletado
        val books = bookRepository.findByCustomer(customer) //busca no repositorio (no banco de dados) todos os livros que pertencem ao customer passado como parametro do metodo e salva na lista books
        for(book in books) { //para cada registro na lista books, vai ser criada a variavel book e vai iterar por ela
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books) //salva todos os livros que estao na lista de uma vez (recebe uma lista de livros - books)
    }

}
