package fr.poec.springboot.instant_faking.DTO;

import fr.poec.springboot.instant_faking.validator.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPostDTO {

    @Email(message = "Please, give a valid email")
    @NotBlank(message = "Please, give an email")
    // On peut réécrire les valeurs des champs de l'annotation
    @UniqueEmail(example = "TOTO, MAIS DU DTO")
    private String username;

    @NotBlank(message = "Please, give a proper name")
    @Size(message = "The account name must have at least 5 characters", min = 5)
    private String name;

    @NotBlank(message = "Please, the password must have a value")
    @Size(message = "The password must have at least 8 characters", min = 8)
    private String password;

}
