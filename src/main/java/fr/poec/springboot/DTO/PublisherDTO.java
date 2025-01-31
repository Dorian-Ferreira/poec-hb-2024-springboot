package fr.poec.springboot.DTO;

import fr.poec.springboot.validator.annotation.DateNotFuture;
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
public class PublisherDTO {

    @NotNull(message = "The date has to be a proper date")
    @DateNotFuture
    private Date createdAt;

    @NotBlank(message = "The name has to have a value")
    private String name;

    @Pattern(
            regexp = "((http?|https|ftp|file)://)?((W|w){3}.)?[a-zA-Z0-9]+\\.[a-zA-Z]+",
            message = "This should be a valid website"
    )
    @NotEmpty(message = "This should be a valid website")
    private String website;

    @DecimalMin(message = "The country id has to be > 0", value = "1")
    @NotNull(message = "The country must be set")
    private Long countryId;
}
