package br.com.ctw.bookstore.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Review returned by the API")
public record ReviewResponseDTO(

        @Schema(
                description = "Identifier of the Review",
                example = "1"
        )
        UUID id,

        @Schema(
                description = "Commentary on the book",
                example = "Too good"
        )
        String comment
) {
}
