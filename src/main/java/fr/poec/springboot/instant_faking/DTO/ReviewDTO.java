package fr.poec.springboot.instant_faking.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @DecimalMin(
        value = "0",
        message= "The rating should be at least 0"
    )
    @DecimalMax(
        value = "5",
        message= "The rating should be 5 at max"
    )
    @NotNull
    private double rating;

    @NotNull(message = "The user is required")
    @Positive(message = "The user id has to be positive")
    private Long userId;

    @NotNull(message = "The game is required")
    @Positive(message = "The game id has to be positive")
    private Long gameId;

}