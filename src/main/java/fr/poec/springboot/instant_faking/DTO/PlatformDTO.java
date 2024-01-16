package fr.poec.springboot.instant_faking.DTO;

import fr.poec.springboot.instant_faking.repository.CountryRepository;
import fr.poec.springboot.instant_faking.repository.PlatformRepository;
import fr.poec.springboot.instant_faking.validator.annotation.UniqueName;
import fr.poec.springboot.instant_faking.validator.group.ValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlatformDTO {

    @NotBlank(
        message = "The name must have a value",
        groups = ValidationGroup.OnPutItem.class
    )
    @UniqueName(
        repositoryClass = PlatformRepository.class,
        groups = ValidationGroup.OnPostItem.class
    )
    private String name;

}