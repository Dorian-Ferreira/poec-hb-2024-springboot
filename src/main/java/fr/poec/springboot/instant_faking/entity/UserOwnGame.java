package fr.poec.springboot.instant_faking.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.instant_faking.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserOwnGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.UserShowView.class)
    private Long id;

    @CreationTimestamp
    @JsonView(JsonViews.UserShowView.class)
    private Date createdAt;

    @JsonView(JsonViews.UserShowView.class)
    private int gameTime;

    @UpdateTimestamp
    @JsonView(JsonViews.UserShowView.class)
    private Date lastUsedAt;

    @JsonView(JsonViews.UserShowView.class)
    private boolean isInstalled;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonView(JsonViews.UserShowView.class)
    private Game game;

}