package fr.poec.springboot.instant_faking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPutDTO {

    private String password;

    private String nickname;

    private String profileImage;

    private Long countryId;

}
