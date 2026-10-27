package br.com.ctw.bookstore.docs;

import br.com.ctw.bookstore.dto.author.AuthorResponseDTO;
import br.com.ctw.bookstore.dto.review.ReviewRequestDTO;
import br.com.ctw.bookstore.dto.review.ReviewRequestUpdateDTO;
import br.com.ctw.bookstore.dto.review.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "Reviews")
public interface ReviewControllerDoc {

    @Operation(
            summary = "Register a new one Review",
            description = "Creates a new review in the system"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Review registered successfully",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Review not registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<ReviewResponseDTO> register(ReviewRequestDTO requestDTO);

    @Operation(
            summary = "List all reviews",
            description = "List all the reviews"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reviews successfully listed",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorResponseDTO.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No review registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Page<ReviewResponseDTO>> listAll(Pageable pageable);

    @Operation(
            summary = "Search by ID",
            description = "Searches for reviews by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Searches for reviews by ID",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No review registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<ReviewResponseDTO> searchById(UUID id);

    @Operation(
            summary = "Search by comment",
            description = "Searches for reviews by comment"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Searches for reviews by comment",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No review registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Optional<ReviewResponseDTO>> searchByComment(String comment);

    @Operation(
            summary = "Search by Book Id",
            description = "Searches for reviews by Book Id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reviews successfully listed",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorResponseDTO.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No review registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Page<ReviewResponseDTO>> searchByBookId(UUID bookId, Pageable pageable);

    @Operation(
            summary = "Updates an review",
            description = "Updates an review by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Review successfully updated",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No review registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<ReviewResponseDTO> update(UUID id, ReviewRequestUpdateDTO dto);
}
