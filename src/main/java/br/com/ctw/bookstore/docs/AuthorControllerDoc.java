package br.com.ctw.bookstore.docs;

import br.com.ctw.bookstore.dto.author.AuthorRequestDTO;
import br.com.ctw.bookstore.dto.author.AuthorRequestUpdateDTO;
import br.com.ctw.bookstore.dto.author.AuthorResponseDTO;
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

@Tag(name = "Authors")
public interface AuthorControllerDoc {

    @Operation(
            summary = "Register a new one Author",
            description = "Creates a new author in the system"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Author registered successfully",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Author not registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<AuthorResponseDTO> register(AuthorRequestDTO requestDTO);

    @Operation(
            summary = "List all authors",
            description = "List all the authors"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authors successfully listed",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthorResponseDTO.class)))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No author registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Page<AuthorResponseDTO>> listAll(Pageable pageable);


    @Operation(
            summary = "Search by ID",
            description = "Searches for authors by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Searches for authors by ID",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No author registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<AuthorResponseDTO> searchById(UUID id);

    @Operation(
            summary = "Search by Name",
            description = "Searches for authors by Name"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Searches for authors by Name",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No author registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<Optional<AuthorResponseDTO>> searchByName(String name);

    @Operation(
            summary = "Updates an author",
            description = "Updates an author by ID"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Author successfully updated",
                            content = @Content(schema = @Schema(implementation = AuthorResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request Error",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No author registered",
                            content = @Content
                    )
            }
    )
    ResponseEntity<AuthorResponseDTO> update(UUID id, AuthorRequestUpdateDTO dto);
}
