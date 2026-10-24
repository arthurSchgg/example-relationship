package br.com.ctw.bookstore.dto.publisher;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
import java.util.UUID;

@Schema(description = "Publisher returned by the API")
public record PublisherResponseDTO(

        @Schema(
                description = "Identifier of the Publisher",
                example = "057f646e-726b-4e5e-9c5b-1a86f27f19d2"
        )
        UUID id,

        @Schema(
                description = "Name of the publisher",
                example = "Patuá Publishing"
        )
        String name,

        @Schema(
                description = "Identifier of the Book",
                example = "057f646e-726b-4e5e-9c5b-1a86f27f19d2"
        )
        Set<UUID> bookId
) {
}
