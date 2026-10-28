package br.com.ctw.bookstore.mapper;

import br.com.ctw.bookstore.dto.author.AuthorRequestDTO;
import br.com.ctw.bookstore.dto.author.AuthorRequestUpdateDTO;
import br.com.ctw.bookstore.dto.author.AuthorResponseDTO;
import br.com.ctw.bookstore.entity.Author;
import br.com.ctw.bookstore.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public AuthorResponseDTO toResponseDTO(Author author) {
        return new AuthorResponseDTO(
                author.getId(),
                author.getName()
        );
    }

    public Author toEntity(AuthorRequestDTO dto) {
        Author author = new Author();

        author.setName(dto.name());

        return author;
    }

    public List<AuthorResponseDTO> toResponseList(List<Author> authors) {
        return authors.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void updateEntity(AuthorRequestUpdateDTO dto, Author author) {

        author.setName(dto.name());

    }
}
