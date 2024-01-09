package fr.poec.springboot.service;

import fr.poec.springboot.DTO.CountryDTO;
import fr.poec.springboot.custom_response.CustomApiResponse;
import fr.poec.springboot.custom_response.CountryCustomApiResponse;
import fr.poec.springboot.custom_response.ErrorCustomApiResponse;
import fr.poec.springboot.entity.Country;
import fr.poec.springboot.repository.CountryRepository;
import fr.poec.springboot.utils.Slug;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {

    private CountryRepository countryRepository;
    private Slug slug;

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
        apiResponse.setObjects(countries);

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
            country = getById(id);
        } catch (NumberFormatException e) {
            country = countryRepository.findBySlug(field);
        }

        if(country.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.addObject(country.get());

            return apiResponse;
        }

        errorApiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorApiResponse.setMessage("This country doesn't exist.");

        return errorApiResponse;
    }


    public CustomApiResponse persist(CountryDTO countryDTO, Long id) {
        CountryCustomApiResponse apiResponse = new CountryCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(Country.class.getSimpleName());
        errorApiResponse.setEntity(Country.class.getSimpleName());

        Country c = countryFromDTO(countryDTO, id);
        if(id != null && c == null) {
            errorApiResponse.setCode(HttpStatus.BAD_REQUEST.value());
            return errorApiResponse;
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.addObject(countryRepository.saveAndFlush(c));

        return apiResponse;
    }

    private Country countryFromDTO(CountryDTO countryDTO, Long id) {
        Country country = new Country();
        Optional<Country> oCountry;
        if(id != null) {
            oCountry = getById(id);
            if(oCountry.isPresent()) {
                country = oCountry.get();
            } else {
                return null;
            }
        }

        country.setCode(countryDTO.getCode().toLowerCase());
        country.setName(countryDTO.getName());
        country.setNationality(countryDTO.getNationality());
        country.setSlug(slug.slugify(countryDTO.getNationality()));
        country.setUrlFlag("https://flagcdn.com/32x24/" + country.getCode() + ".png");

        return country;
    }

    public Optional<Country> getById(Long id) {
        return countryRepository.findById(id);
    }
}
