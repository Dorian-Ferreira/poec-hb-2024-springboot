package fr.poec.springboot.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPostDTO extends UserPutDTO {
    @NotBlank(message = "Please enter something")
    private String name;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Please enter something")
    private String email;
}
