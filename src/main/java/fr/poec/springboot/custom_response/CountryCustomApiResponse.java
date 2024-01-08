package fr.poec.springboot.custom_response;

import fr.poec.springboot.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryCustomApiResponse extends CustomApiResponse {

    private List<Country> objects = new ArrayList<>();

    public void addObject(Country object) {
        objects.add(object);
    }
}