package br.com.ctw.bookstore.service;

import br.com.ctw.bookstore.dto.author.AuthorRequestDTO;
import br.com.ctw.bookstore.dto.author.AuthorRequestUpdateDTO;
import br.com.ctw.bookstore.dto.author.AuthorResponseDTO;
import br.com.ctw.bookstore.entity.Author;
import br.com.ctw.bookstore.entity.Book;
import br.com.ctw.bookstore.mapper.AuthorMapper;
import br.com.ctw.bookstore.repository.AuthorRepository;
import br.com.ctw.bookstore.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper mapper, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public AuthorResponseDTO register(AuthorRequestDTO dto) {
        if (authorRepository.existsByNameIgnoreCase(dto.name())) {
            throw new RuntimeException();
        }

        Set<Book> books = dto.bookId() == null
                ? Set.of()
                : dto.bookId().stream()
                .map(id -> bookRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Livro não encontrado: " + id)))
                .collect(Collectors.toSet());

        Author author = mapper.toEntity(dto, books);

        return mapper.toResponseDTO(authorRepository.save(author));
    }

    @Transactional(readOnly = true)
    public Page<AuthorResponseDTO> listAll(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public AuthorResponseDTO searchById(UUID id) {
        return authorRepository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException());
    }

    @Transactional(readOnly = true)
    public Page<AuthorResponseDTO> searchByBookId(UUID bookId, Pageable pageable) {
        return authorRepository.findByBook_Id(bookId, pageable)
                .map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Optional<AuthorResponseDTO> searchByName(String name) {
        return authorRepository.findByName(name)
                .map(mapper::toResponseDTO);
    }

    @Transactional
    public AuthorResponseDTO update(UUID authorId, AuthorRequestUpdateDTO dto) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found: " + authorId));

        if (authorRepository.existsByNameIgnoreCaseAndIdNot(dto.name(), authorId)) {
            throw new RuntimeException();
        }

        Set<Book> books = dto.bookId().stream()
                .map(id -> bookRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException()))
                .collect(Collectors.toSet());

        author.setName(dto.name());
        author.setBook(books);

        return mapper.toResponseDTO(authorRepository.save(author));
    }

}
