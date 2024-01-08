package fr.poec.springboot.DTO;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPutDTO {
    private String profileImage;

    private String password;

    private String nickname;

    @Positive
    private Long countryId;
}
