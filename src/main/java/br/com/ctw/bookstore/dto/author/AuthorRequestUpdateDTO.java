package br.com.ctw.bookstore.dto.author;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Schema(description = "Input data for updating a Author")
public record AuthorRequestUpdateDTO(

        @Schema(
                description = "Identifier of the Author",
                example = "057f646e-726b-4e5e-9c5b-1a86f27f19d2"
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
