package fr.poec.springboot.instant_faking.service;

import fr.poec.springboot.instant_faking.DTO.PublisherDTO;
import fr.poec.springboot.instant_faking.entity.Publisher;
import fr.poec.springboot.instant_faking.exception.NotFoundInstantFakingException;
import fr.poec.springboot.instant_faking.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService {

    private PublisherRepository publisherRepository;

    private CountryService countryService;

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher show(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isPresent()) {
            return publisherOptional.get();
        }
        throw new NotFoundInstantFakingException("Publisher", "id", id);
    }

    public Publisher persist(PublisherDTO publisherDTO, Long id) {
        if (id != null && publisherRepository.findById(id).isEmpty()) {
            throw new NotFoundInstantFakingException(
                    "Publisher", "id", id
            );
        }

        Publisher p = new Publisher();
        p.setId(id);
        p.setName(publisherDTO.getName());
        p.setCreatedAt(publisherDTO.getCreatedAt());
        p.setWebsite(publisherDTO.getWebsite());
        p.setCountry(countryService.getObjectById(publisherDTO.getCountryId()));

        // Si id = null, le save fera un insert, sinon un update
        return publisherRepository.saveAndFlush(p);
    }
}
