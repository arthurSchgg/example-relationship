package br.com.ctw.bookstore.controller;

import br.com.ctw.bookstore.docs.PublisherControllerDoc;
import br.com.ctw.bookstore.dto.publisher.PublisherRequestDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherRequestUpdateDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherResponseDTO;
import br.com.ctw.bookstore.service.PublisherService;
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
@RequestMapping("/v1/bookstore-publisher")
public class PublisherController implements PublisherControllerDoc {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<PublisherResponseDTO> register(
            @Valid @RequestBody PublisherRequestDTO requestDTO) {

        PublisherResponseDTO newPublisher = service.register(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPublisher.id())
                .toUri();

        return ResponseEntity.created(uri).body(newPublisher);
    }

    @GetMapping
    public ResponseEntity<Page<PublisherResponseDTO>> listAll(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                                  Pageable pageable) {

        return ResponseEntity.ok(service.listAll(pageable));

    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> searchById(@Valid @PathVariable UUID id) {

        return ResponseEntity.ok(service.searchById(id));

    }

    @GetMapping("/search")
    public ResponseEntity<Optional<PublisherResponseDTO>> searchByName(@RequestParam String name) {

        return ResponseEntity.ok(service.searchByName(name));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Page<PublisherResponseDTO>> searchByBookId(@Valid @PathVariable UUID bookId, @PageableDefault(size = 10, sort = "id",
            direction = Sort.Direction.ASC)
    Pageable pageable) {

        return ResponseEntity.ok(service.searchByBookId(bookId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDTO> update(@Valid @PathVariable UUID id, @Valid @RequestBody PublisherRequestUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

}
