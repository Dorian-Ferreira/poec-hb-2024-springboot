package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.ApiResponse;
import fr.poec.springboot.custom_response.CountryApiResponse;
import fr.poec.springboot.entity.Category;
import fr.poec.springboot.entity.Country;
import fr.poec.springboot.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService {

    private CountryRepository countryRepository;

    public ApiResponse findAll() {
        CountryApiResponse apiResponse = new CountryApiResponse();

        apiResponse.setEntity(Country.class.getSimpleName());
        List<Country> countries = countryRepository.findAll();
        if(countries.isEmpty()){
            apiResponse.setCode(HttpStatus.NO_CONTENT.value());
            apiResponse.addObject("There is no countries.");
        } else {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(new ArrayList<>(countries));
        }

        return apiResponse;
    }

    public ApiResponse show(String field) {
        CountryApiResponse apiResponse = new CountryApiResponse();

        apiResponse.setEntity(Country.class.getSimpleName());

        Optional<Country> country;

        try {
            Long id = Long.parseLong(field);
            country = countryRepository.findById(id);
        } catch (NumberFormatException e) {
            country = countryRepository.findBySlug(field);
        }

        if(country.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObjects(Collections.singletonList(country.get()));
        } else {
            apiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            apiResponse.addObject("This country doesn't exist.");
        }

        return apiResponse;
    }
}
