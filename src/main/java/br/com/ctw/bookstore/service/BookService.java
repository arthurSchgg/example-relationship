package br.com.ctw.bookstore.service;

import br.com.ctw.bookstore.dto.book.BookRequestDTO;
import br.com.ctw.bookstore.dto.book.BookResponseDTO;
import br.com.ctw.bookstore.entity.Author;
import br.com.ctw.bookstore.entity.Book;
import br.com.ctw.bookstore.entity.Publisher;
import br.com.ctw.bookstore.mapper.BookMapper;
import br.com.ctw.bookstore.repository.AuthorRepository;
import br.com.ctw.bookstore.repository.BookRepository;
import br.com.ctw.bookstore.repository.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, BookMapper bookMapper, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public BookResponseDTO register(BookRequestDTO dto) {
        Publisher publisher = publisherRepository.findById(dto.publisherID())
                .orElseThrow(() -> new RuntimeException());

        Set<Author> authors = dto.authorsIds().stream()
                .map(id -> authorRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException()))
                .collect(Collectors.toSet());

        Book book = bookMapper.toEntity(dto);

        return bookMapper.toResponseDTO(bookRepository.save(book));
    }
}
