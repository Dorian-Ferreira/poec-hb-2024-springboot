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
public class Platform implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.GamePlatformView.class)
    private Long id;

    @JsonView(JsonViews.GamePlatformMinimalView.class)
    private String name;

    @JsonView(JsonViews.GamePlatformMinimalView.class)
    private String slug;

    @Override
    public String getField() {
        return name;
    }
}