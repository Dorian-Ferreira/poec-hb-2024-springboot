package fr.poec.springboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.json_view.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JsonViews.CountryDetailsView.class)
    private String name;

    private String code;

    private String nationality;

    @JsonView(JsonViews.CountryDetailsView.class)
    private String slug;

    @JsonView(JsonViews.CountryDetailsView.class)
    private String urlFlag;
}