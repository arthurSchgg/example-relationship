        package br.com.ctw.bookstore.dto.book;

        import io.swagger.v3.oas.annotations.media.Schema;
        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.Size;

        import java.util.Locale;
        import java.util.Set;
        import java.util.UUID;

        @Schema(description = "Input data for registering a Book")
        public record BookRequestDTO(

                @Schema(
                        description = "Title of the book",
                        example = "Diary of a Wimpy Kid"
                )
                @NotBlank(message = "The title cannot be empty")
                @Size(max = 300)
                String title,

                @Schema(
                        description = "Identifier of the Publisher",
                        example = "057f646e-726b-4e5e-9c5b-1a86f27f19d2"
                )
                UUID publisherID,

                @Schema(
                        description = "Identifier of the Author",
                        example = "057f646e-726b-4e5e-9c5b-1a86f27f19d2"
                )
                Set<UUID> authorsIds
        ) {
        }
