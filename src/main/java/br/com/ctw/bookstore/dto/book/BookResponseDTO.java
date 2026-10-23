package br.com.ctw.bookstore.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Schema(description = "Book returned by the API")
public record BookResponseDTO(

        @Schema(
                description = "Identifier of the Book",
                example = "1"
        )
        UUID id,

        @Schema(
                description = "Title of the book",
                example = "Diary of a Wimpy Kid"
        )
        @NotBlank(message = "The title cannot be empty")
        @Size(max = 300)
        String title
) {
}
