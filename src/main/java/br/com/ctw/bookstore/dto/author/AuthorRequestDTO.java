package br.com.ctw.bookstore.dto.author;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Input data for registering a Author")
public record AuthorRequestDTO(
        @Schema(
                description = "Name of the author",
                example = "Machado de Assis"
        )
        @NotBlank(message = "The name cannot be empty")
        @Size(max = 200)
        String name
) {
}
