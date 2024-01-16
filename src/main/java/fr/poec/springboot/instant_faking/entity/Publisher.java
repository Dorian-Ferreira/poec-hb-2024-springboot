package fr.poec.springboot.instant_faking.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.instant_faking.entity.interfaces.SluggerInterface;
import fr.poec.springboot.instant_faking.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Publisher implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.PublisherAllShowView.class)
    private Long id;

    @JsonView(JsonViews.PublisherAllShowView.class)
    private Date createdAt;

    @JsonView(JsonViews.PublisherShowView.class)
    private String name;

    @JsonView(JsonViews.PublisherShowView.class)
    private String slug;

    @JsonView(JsonViews.PublisherAllShowView.class)
    private String website;

    @ManyToOne
    @JsonView(JsonViews.PublisherAllShowView.class)
    private Country country;

    @OneToMany(mappedBy = "publisher")
    @JsonView(JsonViews.PublisherAllShowView.class)
    private List<Game> games = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}