package br.com.ctw.bookstore.dto.author;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Set;
import java.util.UUID;

@Schema(description = "Input data for registering a Author")
public record AuthorRequestDTO(
        @Schema(
                description = "Name of the author",
                example = "Machado de Assis"
        )
        @NotBlank(message = "The name cannot be empty")
        @Size(max = 200)
        String name,

        @Schema(
                description = "Identifier of the Book",
                example = "057f646e-726b-4e5e-9c5b-1a86f27f19d2"
        )
        Set<UUID> bookId
) {
}
