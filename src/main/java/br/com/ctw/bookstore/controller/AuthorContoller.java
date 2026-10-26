package br.com.ctw.bookstore.controller;

import br.com.ctw.bookstore.docs.AuthorControllerDoc;
import br.com.ctw.bookstore.dto.author.AuthorRequestDTO;
import br.com.ctw.bookstore.dto.author.AuthorRequestUpdateDTO;
import br.com.ctw.bookstore.dto.author.AuthorResponseDTO;
import br.com.ctw.bookstore.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/bookstore-author")
public class AuthorContoller implements AuthorControllerDoc {

    private final AuthorService service;

    public AuthorContoller(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<AuthorResponseDTO> register(
            @Valid @RequestBody AuthorRequestDTO requestDTO) {

        AuthorResponseDTO newAuthor = service.register(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAuthor.id())
                .toUri();

        return ResponseEntity.created(uri).body(newAuthor);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorResponseDTO>> listAll(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                           Pageable pageable) {
        return ResponseEntity.ok(service.listAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> searchById(@Valid @PathVariable UUID id) {

        return ResponseEntity.ok(service.searchById(id));

    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Page<AuthorResponseDTO>> searchByBookId(@Valid @PathVariable UUID bookId, @PageableDefault(size = 10, sort = "id",
            direction = Sort.Direction.ASC)
    Pageable pageable) {

        return ResponseEntity.ok(service.searchByBookId(bookId, pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<AuthorResponseDTO>> searchByName(@RequestParam String name) {

        return ResponseEntity.ok(service.searchByName(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> update(@Valid @PathVariable UUID id, @Valid @RequestBody AuthorRequestUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
