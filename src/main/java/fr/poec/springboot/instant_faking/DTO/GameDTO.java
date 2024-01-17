package fr.poec.springboot.instant_faking.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.instant_faking.entity.Category;
import fr.poec.springboot.instant_faking.entity.Country;
import fr.poec.springboot.instant_faking.entity.Platform;
import fr.poec.springboot.instant_faking.entity.Publisher;
import fr.poec.springboot.instant_faking.json_views.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDTO {

    private String name;

    private String description;

    private int price;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date publishedAt;

    private String thumbnailCover;

    private Publisher publisher;

    private List<Country> countries = new ArrayList<>();

    private List<Platform> platforms = new ArrayList<>();

    private List<Category> categories = new ArrayList<>();

}
