package fr.poec.springboot.instant_faking.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.instant_faking.entity.interfaces.SluggerInterface;
import fr.poec.springboot.instant_faking.json_views.JsonViews;
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
public class Country implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @JsonView(JsonViews.CountryDetailView.class)
    private String name;

    private String nationality;

    @JsonView(JsonViews.CountryDetailView.class)
    private String slug;

    @JsonView(JsonViews.CountryDetailView.class)
    private String urlFlag;

    @Override
    public String getField() {
        return nationality;
    }
}