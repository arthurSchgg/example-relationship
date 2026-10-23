package br.com.ctw.bookstore.dto.publisher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Schema(description = "Input data for registering a Publisher")
public record PublisherRequestUpdateDTO(

        @Schema(
                description = "Identifier of the Publisher",
                example = "1"
        )
        UUID id,

        @Schema(
                description = "Name of the publisher",
                example = "Patuá Publishing"
        )
        @NotBlank(message = "The name cannot be empty")
        @Size(max = 300)
        String name
) {
}
