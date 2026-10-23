package br.com.ctw.bookstore.mapper;

import br.com.ctw.bookstore.dto.review.ReviewRequestDTO;
import br.com.ctw.bookstore.dto.review.ReviewResponseDTO;
import br.com.ctw.bookstore.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {

    public ReviewResponseDTO toResponseDTO(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getComment()
        );
    }

    public Review toEntity(ReviewRequestDTO dto) {
        Review review = new Review();

        review.setComment(dto.comment());

        return review;
    }

    public List<ReviewResponseDTO> toResponseList(List<Review> reviews) {
        return reviews.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void updateEntity(ReviewResponseDTO dto, Review review) {

        review.setComment(dto.comment());

    }
}
