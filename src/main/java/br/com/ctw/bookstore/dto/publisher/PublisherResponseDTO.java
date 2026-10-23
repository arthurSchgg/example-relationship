package br.com.ctw.bookstore.dto.publisher;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Publisher returned by the API")
public record PublisherResponseDTO(

        @Schema(
                description = "Identifier of the Publisher",
                example = "1"
        )
        UUID id,

        @Schema(
                description = "Name of the publisher",
                example = "Patuá Publishing"
        )
        String name
) {
}
