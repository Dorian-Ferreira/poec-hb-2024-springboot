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
public class Game implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.GameShowView.class)
    private Long id;

    @JsonView(JsonViews.GameMinimalView.class)
    private String name;

    @JsonView(JsonViews.GameShowView.class)
    private String description;

    @JsonView(JsonViews.GameMinimalView.class)
    private int price;

    @JsonView(JsonViews.GameShowView.class)
    private Date publishedAt;

    @JsonView(JsonViews.GameMinimalView.class)
    private String thumbnailCover;

    @JsonView(JsonViews.GameMinimalView.class)
    private String slug;

    @ManyToOne
    @JsonView(JsonViews.GameShowView.class)
    private Publisher publisher; // BDD => publisher_id

    @OneToMany(mappedBy = "game")
    @JsonView(JsonViews.GameShowView.class)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "game_platform",
        joinColumns = @JoinColumn(name = "game_id"), // Nom de la colonne de la table courante
        inverseJoinColumns = @JoinColumn(name = "platform_id") // Nom de la colonne de l'entité en relation
    )
    @JsonView(JsonViews.GameShowView.class)
    private List<Platform> platforms = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "game_category",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonView(JsonViews.GameShowView.class)
    private List<Category> categories = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "game_country",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    @JsonView(JsonViews.GameShowView.class)
    private List<Country> countries = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}