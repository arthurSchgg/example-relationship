package br.com.ctw.bookstore.repository;

import br.com.ctw.bookstore.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    Optional<Review> findByComment(String comment);

    Page<Review> findByBook_Id(UUID bookId, Pageable pageable);

    boolean existsByBook_Id(UUID bookId);

    boolean existsByBook_IdAndIdNot(UUID bookId, UUID reviewId);

}
