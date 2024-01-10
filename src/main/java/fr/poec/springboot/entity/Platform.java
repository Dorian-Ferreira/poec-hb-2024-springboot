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
public class Platform implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.IgnoreView.class)
    private Long id;

    @JsonView(JsonViews.GameShowView.class)
    private String name;

    @JsonView(JsonViews.GameShowView.class)
    private String slug;

    @Override
    @JsonView(JsonViews.IgnoreView.class)
    public String getField() {
        return name;
    }
}