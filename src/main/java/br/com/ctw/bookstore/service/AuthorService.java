package br.com.ctw.bookstore.service;

import br.com.ctw.bookstore.dto.author.AuthorRequestDTO;
import br.com.ctw.bookstore.dto.author.AuthorRequestUpdateDTO;
import br.com.ctw.bookstore.dto.author.AuthorResponseDTO;
import br.com.ctw.bookstore.entity.Author;
import br.com.ctw.bookstore.mapper.AuthorMapper;
import br.com.ctw.bookstore.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Transactional
    public AuthorResponseDTO register(AuthorRequestDTO dto) {
        if (authorRepository.existsByNameIgnoreCase(dto.name())) {
            throw new RuntimeException();
        }

        Author author = mapper.toEntity(dto);

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
    public AuthorResponseDTO update(UUID id, AuthorRequestUpdateDTO dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        if (authorRepository.existsByNameIgnoreCaseAndIdNot(dto.name(), id)) {
            throw new RuntimeException();
        }

        author.setName(dto.name());

        return mapper.toResponseDTO(authorRepository.save(author));
    }

}
