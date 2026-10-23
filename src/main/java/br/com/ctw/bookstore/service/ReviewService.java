package br.com.ctw.bookstore.service;

import br.com.ctw.bookstore.dto.review.ReviewRequestDTO;
import br.com.ctw.bookstore.dto.review.ReviewRequestUpdateDTO;
import br.com.ctw.bookstore.dto.review.ReviewResponseDTO;
import br.com.ctw.bookstore.entity.Book;
import br.com.ctw.bookstore.entity.Review;
import br.com.ctw.bookstore.mapper.ReviewMapper;
import br.com.ctw.bookstore.repository.BookRepository;
import br.com.ctw.bookstore.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final BookRepository bookRepository;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public ReviewResponseDTO register(ReviewRequestDTO dto){
        if (reviewRepository.existsByBook_Id(dto.bookId())) {
            throw new RuntimeException();
        }

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new RuntimeException());

        Review review = reviewMapper.toEntity(dto, book);

        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toResponseDTO(savedReview);
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponseDTO> listAll(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(reviewMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public ReviewResponseDTO searchById(UUID id){
        return reviewRepository.findById(id)
                .map(reviewMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException());
    }

    @Transactional(readOnly = true)
    public Optional<ReviewResponseDTO> searchByComment(String comment) {
        return reviewRepository.findByComment(comment)
                .map(reviewMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponseDTO> searchByBookId(UUID bookId, Pageable pageable) {
        return reviewRepository.findByBook_Id(bookId, pageable)
                .map(reviewMapper::toResponseDTO);
    }

    @Transactional
    public ReviewResponseDTO update(UUID id, ReviewRequestUpdateDTO dto) {
        if (reviewRepository.existsByBook_IdAndIdNot(dto.bookId(), id)) {
            throw new RuntimeException();
        }

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new RuntimeException());

        review.setComment(dto.comment());
        review.setBook(book);

        Review updatedReview = reviewRepository.save(review);

        return reviewMapper.toResponseDTO(updatedReview);
    }
}
