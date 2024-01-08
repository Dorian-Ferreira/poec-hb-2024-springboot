package fr.poec.springboot.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String nationality;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]{2}", message = "The code needs to be 2 characters long.")
    private String code;

}
