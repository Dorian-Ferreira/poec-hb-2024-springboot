package fr.poec.springboot.DTO;

import fr.poec.springboot.validator.annotation.DateNotFuture;
import fr.poec.springboot.validator.annotation.ExistingGame;
import fr.poec.springboot.validator.annotation.ExistingUser;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    @NotBlank(message = "The title has to have a value")
    private String title;

    @NotBlank(message = "The content has to have a value")
    private String content;

    @DecimalMin(message = "The rating should be between 0 and 5", value = "0")
    @DecimalMax(message = "The rating should be between 0 and 5", value = "5")
    @NotNull(message = "The rating has to have a value")
    private double rating;

    @ExistingUser
    @NotNull(message = "The User must be set")
    private Long userId;

    @ExistingGame
    @NotNull(message = "The Game must be set")
    private Long gameId;
}
