package br.com.ctw.bookstore.controller;

import br.com.ctw.bookstore.dto.book.BookRequestDTO;
import br.com.ctw.bookstore.dto.book.BookRequestUpdateDTO;
import br.com.ctw.bookstore.dto.book.BookResponseDTO;
import br.com.ctw.bookstore.service.BookService;
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

@Tag(name = "Books' controller", description = "Books Endpoints")
@RestController
@RequestMapping("/v1/bookstore-book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> register(@RequestBody(required = true) BookRequestDTO requestDTO) {
        BookResponseDTO newBook = service.register(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(newBook.id())
                .toUri();

        return ResponseEntity.created(uri).body(newBook);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> listAll(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                         Pageable pageable) {

        return ResponseEntity.ok(service.listAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> searchById(@Valid @PathVariable UUID id) {

        return ResponseEntity.ok(service.searchById(id));

    }

    @GetMapping("/search")
    public ResponseEntity<Optional<BookResponseDTO>> searchByTitle(@RequestParam String title) {

        return ResponseEntity.ok(service.searchByTitle(title));

    }

    @GetMapping("/authors/{authorsId}")
    public ResponseEntity<Page<BookResponseDTO>> searchByAuthorsId(@Valid @PathVariable UUID authorsId, @PageableDefault(size = 10, sort = "id",
            direction = Sort.Direction.ASC)
    Pageable pageable) {

        return ResponseEntity.ok(service.searchByAuthorsId(authorsId, pageable));

    }

    @GetMapping("/publishers/{publishersId}")
    public ResponseEntity<Page<BookResponseDTO>> searchByPublishersID(@Valid @PathVariable UUID publishersId, @PageableDefault(size = 10, sort = "id",
            direction = Sort.Direction.ASC)
    Pageable pageable) {

        return ResponseEntity.ok(service.searchByPublishersId(publishersId, pageable));

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> update(@Valid @PathVariable UUID id, @Valid @RequestBody BookRequestUpdateDTO dto) {

        return ResponseEntity.ok(service.update(id, dto));

    }
}
