package br.com.ctw.bookstore.mapper;

import br.com.ctw.bookstore.dto.book.BookRequestDTO;
import br.com.ctw.bookstore.dto.book.BookRequestUpdateDTO;
import br.com.ctw.bookstore.dto.book.BookResponseDTO;
import br.com.ctw.bookstore.entity.Author;
import br.com.ctw.bookstore.entity.Book;
import br.com.ctw.bookstore.entity.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class BookMapper {

    public BookResponseDTO toResponseDTO(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getTitle()
        );
    }

    public Book toEntity(BookRequestDTO dto, Publisher publisher, Set<Author> authors) {

        Book book = new Book();

        book.setTitle(dto.title());
        book.setPublisher(publisher);
        book.setAuthors(authors);

        return book;
    }

    public List<BookResponseDTO> toResponseList(List<Book> books) {
        return books.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void updateEntity(BookRequestUpdateDTO dto, Book book) {

        book.setTitle(dto.title());

    }
}
