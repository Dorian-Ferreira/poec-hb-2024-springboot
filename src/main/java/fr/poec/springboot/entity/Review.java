package fr.poec.springboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.json_view.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.ReviewListView.class)
    private Long id;


    @JsonView(JsonViews.ReviewMinimalView.class)
    private String title;


    @JsonView(JsonViews.ReviewMinimalView.class)
    private String content;


    @JsonView(JsonViews.ReviewMinimalView.class)
    private Date createdAt;


    @JsonView(JsonViews.ReviewMinimalView.class)
    private int downVote;


    @JsonView(JsonViews.ReviewMinimalView.class)
    private int upVote;


    @JsonView(JsonViews.ReviewMinimalView.class)
    private double rating;

    @ManyToOne
    @JsonView({
            JsonViews.GameShowView.class,
            JsonViews.ReviewListView.class
    })
    private User user;

    @ManyToOne
    @JsonView({
            JsonViews.ReviewListView.class,
            JsonViews.UserShowView.class
    })
    private Game game;
}