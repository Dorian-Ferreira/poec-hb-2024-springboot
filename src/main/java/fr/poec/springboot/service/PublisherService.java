package fr.poec.springboot.service;

import fr.poec.springboot.DTO.CountryDTO;
import fr.poec.springboot.DTO.PublisherDTO;
import fr.poec.springboot.custom_response.CountryCustomApiResponse;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.custom_response.PublisherCustomApiResponse;
import fr.poec.springboot.entity.Country;
import fr.poec.springboot.entity.Publisher;
import fr.poec.springboot.repository.CountryRepository;
import fr.poec.springboot.repository.PublisherRepository;
import fr.poec.springboot.utils.Slug;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService {

    private PublisherRepository publisherRepository;
    private CountryService countryService;
    private Slug slug;

    public CustomApiResponse show(String field) {
        PublisherCustomApiResponse apiResponse = new PublisherCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Publisher.class.getSimpleName());
        errorApiResponse.setEntity(Publisher.class.getSimpleName());

        Optional<Publisher> publisher;

        try {
            Long id = Long.parseLong(field);
            publisher = getById(id);
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


    public CustomApiResponse create(PublisherDTO publisherDTO, Long id) {
        PublisherCustomApiResponse apiResponse = new PublisherCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Publisher.class.getSimpleName());
        errorApiResponse.setEntity(Publisher.class.getSimpleName());

        Publisher p = publisherFromDTO(publisherDTO, id);
        if(id != null && p == null) {
            errorApiResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return errorApiResponse;
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObject(publisherRepository.saveAndFlush(p));

        return apiResponse;
    }

    private Publisher publisherFromDTO(PublisherDTO publisherDTO, Long id) {
        Publisher publisher = new Publisher();
        Optional<Publisher> oPublisher;
        if(id != null) {
            oPublisher = getById(id);
            if(oPublisher.isPresent()) {
                publisher = oPublisher.get();
            } else {
                return null;
            }
        }

        publisher.setName(publisherDTO.getName());
        publisher.setSlug(slug.slugify(publisherDTO.getName()));
        publisher.setCountry(countryService.getById(publisherDTO.getCountryId()).get());
        publisher.setCreatedAt(publisherDTO.getCreatedAt());
        publisher.setWebsite(publisherDTO.getWebsite());

        return publisher;
    }

    public Optional<Publisher> getById(Long id) {
        return this.publisherRepository.findById(id);
    }
}
