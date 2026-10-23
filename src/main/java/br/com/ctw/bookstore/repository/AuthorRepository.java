package br.com.ctw.bookstore.repository;

import br.com.ctw.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByName(String name);

    Page<Author> findByBook_Id(UUID bookId, Pageable pageable);

    boolean existsByNameIgnoreCase(String nome);

    boolean existsByNameIgnoreCaseAndIdNot(String nome, UUID id);

}
