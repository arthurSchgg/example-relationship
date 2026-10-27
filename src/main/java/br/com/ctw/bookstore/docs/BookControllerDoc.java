package br.com.ctw.bookstore.docs;

import br.com.ctw.bookstore.dto.author.AuthorResponseDTO;
import br.com.ctw.bookstore.dto.book.BookRequestDTO;
import br.com.ctw.bookstore.dto.book.BookRequestUpdateDTO;
import br.com.ctw.bookstore.dto.book.BookResponseDTO;
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

@Tag(name = "Books")
public interface BookControllerDoc {

    @Operation(
            summary = "Register a new one Book",
            description = "Creates a new book in the system"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Book registered successfully",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<BookResponseDTO> register(BookRequestDTO requestDTO);

    @Operation(
            summary = "List all books",
            description = "List all the books"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Books successfully listed",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorResponseDTO.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No book registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Page<BookResponseDTO>> listAll(Pageable pageable);

    @Operation(
            summary = "Search by ID",
            description = "Searches for books by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Searches for books by ID",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No book registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<BookResponseDTO> searchById(UUID id);

    @Operation(
            summary = "Search by title",
            description = "Searches for books by title"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Searches for books by title",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No book registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Optional<BookResponseDTO>> searchByTitle(String title);

    @Operation(
            summary = "Search by Author Id",
            description = "Searches for books by Author Id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Books successfully listed",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorResponseDTO.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No book registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Page<BookResponseDTO>> searchByAuthorsId(UUID authorsId, Pageable pageable);

    @Operation(
            summary = "Search by Publisher Id",
            description = "Searches for books by Publisher Id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Books successfully listed",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorResponseDTO.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No book registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Page<BookResponseDTO>> searchByPublishersID(UUID publishersId, Pageable pageable);

    @Operation(
            summary = "Updates an book",
            description = "Updates an book by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book successfully updated",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No book registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<BookResponseDTO> update(UUID id, BookRequestUpdateDTO dto);
}
