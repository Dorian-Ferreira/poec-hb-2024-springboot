package fr.poec.springboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.json_view.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
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
    @JsonView(JsonViews.IgnoreView.class)
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
    @JsonView(JsonViews.IgnoreView.class)
    private User user;

    @ManyToOne
    @JsonView(JsonViews.UserShowView.class)
    private Game game;
}