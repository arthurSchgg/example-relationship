package br.com.ctw.bookstore.dto.author;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Schema(description = "Input data for updating a Author")
public record AuthorRequestUpdateDTO(

        @Schema(
                description = "Identifier of the Author",
                example = "1"
        )
        UUID id,

        @Schema(
                description = "Name of the author",
                example = "Machado de Assis"
        )
        @NotBlank(message = "The name cannot be empty")
        @Size(max = 200)
        String name
) {
}
