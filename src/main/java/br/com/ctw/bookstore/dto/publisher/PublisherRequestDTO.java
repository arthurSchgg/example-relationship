package br.com.ctw.bookstore.dto.publisher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Input data for registering a Publisher")
public record PublisherRequestDTO(

        @Schema(
                description = "Name of the publisher",
                example = "Patuá Publishing"
        )
        @NotBlank(message = "The name cannot be empty")
        @Size(max = 300)
        String name
) {
}
