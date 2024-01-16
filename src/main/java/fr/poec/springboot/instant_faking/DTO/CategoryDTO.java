package fr.poec.springboot.instant_faking.DTO;

import fr.poec.springboot.instant_faking.repository.CategoryRepository;
import fr.poec.springboot.instant_faking.validator.annotation.UniqueName;
import fr.poec.springboot.instant_faking.validator.group.ValidationGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    @NotBlank(
        message = "Name is required",
        groups = ValidationGroup.OnPutItem.class
    )
    @UniqueName(
        repositoryClass = CategoryRepository.class,
        groups = ValidationGroup.OnPostItem.class
    )
    private String name;

    private String image;

}