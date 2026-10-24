package br.com.ctw.bookstore.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Schema(description = "Input data for updating a Review")
public record ReviewRequestUpdateDTO(

        @Schema(
                description = "Identifier of the Review",
                example = "057f646e-726b-4e5e-9c5b-1a86f27f19d2"
        )
        UUID id,

        @Schema(
                description = "Commentary on the book",
                example = "Too good"
        )
        @NotBlank(message = "The comment cannot be empty")
        @Size(max = 1000)
        String comment,

        @Schema(
                description = "Identifier of the Book",
                example = "057f646e-726b-4e5e-9c5b-1a86f27f19d2"
        )
        UUID bookId
) {
}
