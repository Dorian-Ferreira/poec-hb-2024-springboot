package fr.poec.springboot.DTO;

import fr.poec.springboot.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryDTO {

    private String name;
    private String urlFlag;
    private String slug;
}
