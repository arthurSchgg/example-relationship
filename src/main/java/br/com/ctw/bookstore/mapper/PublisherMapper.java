package br.com.ctw.bookstore.mapper;

import br.com.ctw.bookstore.dto.publisher.PublisherRequestDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherRequestUpdateDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherResponseDTO;
import br.com.ctw.bookstore.entity.Author;
import br.com.ctw.bookstore.entity.Book;
import br.com.ctw.bookstore.entity.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PublisherMapper {

    public PublisherResponseDTO toResponseDTO(Publisher publisher) {
        return new PublisherResponseDTO(
                publisher.getId(),
                publisher.getName(),
                publisher.getBook().stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet())
        );
    }

    public Publisher toEntity(PublisherRequestDTO dto, Set<Book> book) {
        Publisher publisher = new Publisher();

        publisher.setName(dto.name());
        publisher.setBook(book);

        return publisher;
    }

    public List<PublisherResponseDTO> toResponseList(List<Publisher> publishers) {
        return publishers.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void updateEntity(PublisherRequestUpdateDTO dto, Publisher publisher) {

        publisher.setName(dto.name());

    }
}
