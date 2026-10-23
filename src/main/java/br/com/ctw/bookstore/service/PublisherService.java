package br.com.ctw.bookstore.service;

import br.com.ctw.bookstore.dto.publisher.PublisherRequestDTO;
import br.com.ctw.bookstore.dto.publisher.PublisherResponseDTO;
import br.com.ctw.bookstore.entity.Publisher;
import br.com.ctw.bookstore.mapper.PublisherMapper;
import br.com.ctw.bookstore.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PublisherService {

    private final PublisherRepository repository;
    private final PublisherMapper mapper;

    public PublisherService(PublisherRepository repository, PublisherMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public PublisherResponseDTO register(PublisherRequestDTO dto){
        if(repository.existsByNameIgnoreCase(dto.name())){
            throw new RuntimeException();
        }

        Publisher publisher = mapper.toEntity(dto);

        Publisher savePublisher = repository.save(publisher);

        return mapper.toResponseDTO(savePublisher);
    }

    @Transactional(readOnly = true)
    public Page<PublisherResponseDTO> listAll(Pageable pageable){
        return repository.findAll(pageable)
                .map(mapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public PublisherResponseDTO searchById(UUID id){
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException());
    }
}
