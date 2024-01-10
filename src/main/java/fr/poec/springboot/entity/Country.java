package fr.poec.springboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.entity.interfaces.SluggerInterface;
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
public class Country implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.IgnoreView.class)
    private Long id;

    @JsonView(JsonViews.CountryDetailsView.class)
    private String name;

    @JsonView(JsonViews.IgnoreView.class)
    private String code;

    @JsonView(JsonViews.IgnoreView.class)
    private String nationality;

    @JsonView(JsonViews.CountryDetailsView.class)
    private String slug;

    @JsonView(JsonViews.CountryDetailsView.class)
    private String urlFlag;

    @Override
    @JsonView(JsonViews.IgnoreView.class)
    public String getField() {
        return nationality;
    }
}