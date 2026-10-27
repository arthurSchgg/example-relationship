package br.com.ctw.bookstore.docs;

import br.com.ctw.bookstore.dto.author.AuthorResponseDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherRequestDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherRequestUpdateDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherResponseDTO;
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

@Tag(name = "Publishers")
public interface PublisherControllerDoc {

    @Operation(
            summary = "Register a new one Publisher",
            description = "Creates a new publisher in the system"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Publisher registered successfully",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Publisher not registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<PublisherResponseDTO> register(PublisherRequestDTO requestDTO);

    @Operation(
            summary = "List all publishers",
            description = "List all the publishers"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Publishers successfully listed",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorResponseDTO.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No publisher registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Page<PublisherResponseDTO>> listAll(Pageable pageable);

    @Operation(
            summary = "Search by ID",
            description = "Searches for publishers by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Searches for publishers by ID",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No publisher registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<PublisherResponseDTO> searchById(UUID id);

    @Operation(
            summary = "Search by name",
            description = "Searches for books by name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Searches for books by name",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No publisher registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Optional<PublisherResponseDTO>> searchByName(String name);

    @Operation(
            summary = "Search by Book Id",
            description = "Searches for publishers by Book Id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Publishers successfully listed",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorResponseDTO.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No publisher registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Page<PublisherResponseDTO>> searchByBookId(UUID bookId, Pageable pageable);

    @Operation(
            summary = "Updates an publisher",
            description = "Updates an publisher by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Publisher successfully updated",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No publisher registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<PublisherResponseDTO> update(UUID id, PublisherRequestUpdateDTO dto);
}
