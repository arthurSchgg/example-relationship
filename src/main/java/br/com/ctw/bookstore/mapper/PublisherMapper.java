package br.com.ctw.bookstore.mapper;

import br.com.ctw.bookstore.dto.publisher.PublisherRequestDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherRequestUpdateDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherResponseDTO;
import br.com.ctw.bookstore.entity.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublisherMapper {

    public PublisherResponseDTO toResponseDTO(Publisher publisher) {
        return new PublisherResponseDTO(
                publisher.getId(),
                publisher.getName()
        );
    }

    public Publisher toEntity(PublisherRequestDTO dto) {
        Publisher publisher = new Publisher();

        publisher.setName(dto.name());

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
