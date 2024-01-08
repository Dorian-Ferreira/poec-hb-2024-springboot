package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.PublisherCustomApiResponse;
import fr.poec.springboot.entity.Publisher;
import fr.poec.springboot.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService {

    private PublisherRepository publisherRepository;

    public CustomApiResponse show(String field) {
        PublisherCustomApiResponse apiResponse = new PublisherCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Publisher.class.getSimpleName());
        errorApiResponse.setEntity(Publisher.class.getSimpleName());

        Optional<Publisher> publisher;

        try {
            Long id = Long.parseLong(field);
            publisher = this.publisherRepository.findById(id);
        } catch (NumberFormatException e) {
            publisher = this.publisherRepository.findBySlug(field);
        }

        if(publisher.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObject(publisher.get());

            return apiResponse;
        }

        errorApiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorApiResponse.setMessage("This Publisher doesn't exist.");

        return errorApiResponse;
    }
}
