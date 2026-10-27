package br.com.ctw.bookstore.service;

import br.com.ctw.bookstore.dto.book.BookRequestDTO;
import br.com.ctw.bookstore.dto.book.BookRequestUpdateDTO;
import br.com.ctw.bookstore.dto.book.BookResponseDTO;
import br.com.ctw.bookstore.entity.Author;
import br.com.ctw.bookstore.entity.Book;
import br.com.ctw.bookstore.entity.Publisher;
import br.com.ctw.bookstore.exception.AuthorNotFoundException;
import br.com.ctw.bookstore.exception.BookNotFoundException;
import br.com.ctw.bookstore.exception.PublisherNotFoundException;
import br.com.ctw.bookstore.mapper.BookMapper;
import br.com.ctw.bookstore.repository.AuthorRepository;
import br.com.ctw.bookstore.repository.BookRepository;
import br.com.ctw.bookstore.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
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
        if (bookRepository.existsByTitleIgnoreCase(dto.title())) {
            throw new RuntimeException();
        }

        Publisher publisher = publisherRepository.findById(dto.publisherID())
                .orElseThrow(() -> new PublisherNotFoundException("Publisher not found: " + dto.publisherID()));

        Set<Author> authors = dto.authorsIds().stream()
                .map(id -> authorRepository.findById(id)
                        .orElseThrow(() -> new AuthorNotFoundException("Author not found: " + dto.authorsIds())))
                .collect(Collectors.toSet());

        Book book = bookMapper.toEntity(dto, publisher, authors);

        Book savedBook = bookRepository.save(book);

        return bookMapper.toResponseDTO(savedBook);
    }

    @Transactional(readOnly = true)
    public Page<BookResponseDTO> listAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public BookResponseDTO searchById(UUID id) {
        return bookRepository.findById(id)
                .map(bookMapper::toResponseDTO)
                .orElseThrow(() -> new BookNotFoundException("Book not found: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<BookResponseDTO> searchByTitle(String title) {
        return bookRepository.findByTitle(title)
                .map(bookMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<BookResponseDTO> searchByAuthorsId(UUID authorId, Pageable pageable) {
        return bookRepository.findByAuthors_Id(authorId, pageable)
                .map(bookMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<BookResponseDTO> searchByPublishersId(UUID publisherId, Pageable pageable) {
        return bookRepository.findByPublisher_Id(publisherId, pageable)
                .map(bookMapper::toResponseDTO);
    }

    @Transactional
    public BookResponseDTO update(UUID bookId, BookRequestUpdateDTO dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found: " + bookId));

        Publisher publisher = publisherRepository.findById(dto.publisherID())
                .orElseThrow(() -> new PublisherNotFoundException("Publisher not found: " + dto.publisherID()));

        Set<Author> authors = dto.authorsIds().stream()
                .map(id -> authorRepository.findById(id)
                        .orElseThrow(() -> new AuthorNotFoundException("Author not found: " + dto.authorsIds())))
                .collect(Collectors.toSet());

        if (bookRepository.existsByTitleIgnoreCaseAndIdNot(dto.title(), bookId)) {
            throw new RuntimeException();
        }

        book.setTitle(dto.title());
        book.setPublisher(publisher);
        book.setAuthors(authors);

        Book updatedBook = bookRepository.save(book);

        return bookMapper.toResponseDTO(updatedBook);

    }
}
