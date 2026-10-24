package br.com.ctw.bookstore.service;

import br.com.ctw.bookstore.dto.publisher.PublisherRequestDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherRequestUpdateDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherResponseDTO;
import br.com.ctw.bookstore.entity.Author;
import br.com.ctw.bookstore.entity.Book;
import br.com.ctw.bookstore.entity.Publisher;
import br.com.ctw.bookstore.mapper.PublisherMapper;
import br.com.ctw.bookstore.repository.BookRepository;
import br.com.ctw.bookstore.repository.PublisherRepository;
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
public class PublisherService {

    private final PublisherRepository repository;
    private final PublisherMapper mapper;
    private final BookRepository bookRepository;

    public PublisherService(PublisherRepository repository, PublisherMapper mapper, BookRepository bookRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public PublisherResponseDTO register(PublisherRequestDTO dto) {
        if (repository.existsByNameIgnoreCase(dto.name())) {
            throw new RuntimeException();
        }

        Set<Book> books = dto.bookId() == null
                ? Set.of()
                : dto.bookId().stream()
                .map(id -> bookRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Livro não encontrado: " + id)))
                .collect(Collectors.toSet());

        Publisher publisher = mapper.toEntity(dto, books);

        Publisher savePublisher = repository.save(publisher);

        return mapper.toResponseDTO(savePublisher);
    }

    @Transactional(readOnly = true)
    public Page<PublisherResponseDTO> listAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public PublisherResponseDTO searchById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException());
    }

    @Transactional(readOnly = true)
    public Optional<PublisherResponseDTO> searchByName(String name) {
        return repository.findByName(name)
                .map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<PublisherResponseDTO> searchByBookId(UUID bookId, Pageable pageable) {
        return repository.findByBook_Id(bookId, pageable)
                .map(mapper::toResponseDTO);
    }

    @Transactional
    public PublisherResponseDTO update(UUID publisherId, PublisherRequestUpdateDTO dto) {
        Publisher publisher = repository.findById(publisherId)
                .orElseThrow(() -> new RuntimeException());

        if (repository.existsByNameIgnoreCaseAndIdNot(dto.name(), publisherId)) {
            throw new RuntimeException();
        }

        Set<Book> books = dto.bookId().stream()
                .map(id -> bookRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException()))
                .collect(Collectors.toSet());

        publisher.setName(dto.name());
        publisher.setBook(books);

        Publisher updatedPublisher = repository.save(publisher);

        return mapper.toResponseDTO(updatedPublisher);

    }
}
