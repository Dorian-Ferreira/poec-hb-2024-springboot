package fr.poec.springboot.service;

import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.CountryCustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.entity.Country;
import fr.poec.springboot.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {

    private CountryRepository countryRepository;

    public CustomApiResponse findAll() {
        CountryCustomApiResponse apiResponse = new CountryCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Country.class.getSimpleName());
        errorApiResponse.setEntity(Country.class.getSimpleName());

        List<Country> countries = countryRepository.findAll();
        if(countries.isEmpty()) {
            errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
            errorApiResponse.setMessage("There is no countries.");

            return errorApiResponse;
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObjects(new ArrayList<>(countries));

        return apiResponse;
    }

    public CustomApiResponse show(String field) {
        CountryCustomApiResponse apiResponse = new CountryCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Country.class.getSimpleName());
        errorApiResponse.setEntity(Country.class.getSimpleName());

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

            return apiResponse;
        }

        errorApiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorApiResponse.setMessage("This country doesn't exist.");

        return errorApiResponse;
    }
}
