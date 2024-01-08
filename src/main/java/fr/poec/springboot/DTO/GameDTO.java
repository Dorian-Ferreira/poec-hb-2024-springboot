package fr.poec.springboot.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDTO {

    private String name;
    private String slug;
    private String thumbnailCover;
    private int price;

}
