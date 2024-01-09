package fr.poec.springboot.DTO;

import fr.poec.springboot.validator.annotation.ExistingGame;
import fr.poec.springboot.validator.annotation.ExistingUser;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserOwnGameDTO {

    @ExistingUser
    @NotNull(message = "The User must be set")
    private Long userId;

    @ExistingGame
    @NotNull(message = "The Game must be set")
    private Long gameId;
}
