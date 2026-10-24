package br.com.ctw.bookstore.controller;

import br.com.ctw.bookstore.dto.publisher.PublisherRequestUpdateDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherResponseDTO;
import br.com.ctw.bookstore.dto.review.ReviewRequestDTO;
import br.com.ctw.bookstore.dto.review.ReviewRequestUpdateDTO;
import br.com.ctw.bookstore.dto.review.ReviewResponseDTO;
import br.com.ctw.bookstore.service.ReviewService;
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

@Tag(name = "Reviews' controller", description = "Reviews Endpoints")
@RestController
@RequestMapping("/v1/bookstore-review")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> register(@RequestBody(required = true) ReviewRequestDTO requestDTO) {

        ReviewResponseDTO newReview = service.register(requestDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(newReview.id())
                .toUri();

        return ResponseEntity.created(uri).body(newReview);

    }

    @GetMapping
    public ResponseEntity<Page<ReviewResponseDTO>> listAll(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
                                                              Pageable pageable) {

        return ResponseEntity.ok(service.listAll(pageable));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> searchById(@Valid @PathVariable UUID id) {

        return ResponseEntity.ok(service.searchById(id));

    }

    @GetMapping("/search")
    public ResponseEntity<Optional<ReviewResponseDTO>> searchByComment(@RequestParam String comment) {

        return ResponseEntity.ok(service.searchByComment(comment));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Page<ReviewResponseDTO>> searchByBookId(@Valid @PathVariable UUID bookId, @PageableDefault(size = 10, sort = "id",
            direction = Sort.Direction.ASC)
    Pageable pageable) {

        return ResponseEntity.ok(service.searchByBookId(bookId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> update(@Valid @PathVariable UUID id, @Valid @RequestBody ReviewRequestUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
}
