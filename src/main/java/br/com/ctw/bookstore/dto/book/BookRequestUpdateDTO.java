package br.com.ctw.bookstore.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Schema(description = "Input data for updating a Book")
public record BookRequestUpdateDTO(

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
        String title,

        @Schema(
                description = "Identifier of the Publisher",
                example = "1"
        )
        UUID publisherID,

        @Schema(
                description = "Identifier of the Author",
                example = "1"
        )
        Set<UUID> authorsIds
) {
}
