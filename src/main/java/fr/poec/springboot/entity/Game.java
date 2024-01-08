package fr.poec.springboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.json_view.JsonViews;
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
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.GameShowView.class)
    private Long id;

    @JsonView(JsonViews.GameListView.class)
    private String name;

    @JsonView(JsonViews.GameShowView.class)
    private String description;

    @JsonView(JsonViews.GameListView.class)
    private int price;

    @JsonView(JsonViews.GameShowView.class)
    private Date publishedAt;

    @JsonView(JsonViews.GameListView.class)
    private String slug;

    @JsonView(JsonViews.GameListView.class)
    private String thumbnailCover;

    @ManyToOne
    @JsonView(JsonViews.GameShowView.class)
    private Publisher publisher;

    @OneToMany(mappedBy = "game")
    @JsonView(JsonViews.IgnoreView.class)
    private List<UserOwnGame> owners = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "game_category",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "category_id")
            })
    @JsonView(JsonViews.GameShowView.class)
    private List<Category> categories = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "game_platform",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "platform_id")
            })
    @JsonView(JsonViews.GameShowView.class)
    private List<Platform> platforms = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "game_country",
            joinColumns = {
                    @JoinColumn(name = "game_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "country_id")
            })
    @JsonView(JsonViews.GameShowView.class)
    private List<Country> countries = new ArrayList<>();

    @OneToMany(mappedBy = "game")
    @JsonView(JsonViews.GameShowView.class)
    private List<Review> reviews = new ArrayList<>();
}