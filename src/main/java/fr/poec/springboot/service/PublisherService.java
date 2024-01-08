package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.custom_response.PublisherApiResponse;
import fr.poec.springboot.entity.Publisher;
import fr.poec.springboot.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService {

    private PublisherRepository publisherRepository;

    public ApiResponse show(String field) {
        PublisherApiResponse apiResponse = new PublisherApiResponse();

        apiResponse.setEntity(Publisher.class.getSimpleName());

        Optional<Publisher> publisher;

        try {
            Long id = Long.parseLong(field);
            publisher = this.publisherRepository.findById(id);
        } catch (NumberFormatException e) {
            publisher = this.publisherRepository.findBySlug(field);
        }

        if(publisher.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(Collections.singletonList(publisher.get()));
        } else {
            apiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            apiResponse.addObject("This game doesn't exist.");
        }

        return apiResponse;
    }
}
