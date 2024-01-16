package fr.poec.springboot.instant_faking.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.instant_faking.json_views.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.UserShowView.class)
    private Long id;

    @CreationTimestamp
    @JsonView(JsonViews.UserShowView.class)
    private Date createdAt;

    @JsonView(JsonViews.UserShowView.class)
    private String email;

    @JsonView(JsonViews.UserMinimalView.class)
    private String name;

    @JsonView(JsonViews.UserShowView.class)
    private String nickname;

    private String password;

    @JsonView(JsonViews.UserMinimalView.class)
    private String profileImage;

    private String roles = "[]";

    @JsonView(JsonViews.UserShowView.class)
    private double wallet = 0;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserShowView.class)
    private List<UserOwnGame> userOwnGames = new ArrayList<>();

    @ManyToOne
    @JsonView(JsonViews.UserShowView.class)
    private Country country;

    @ManyToMany
    @JoinTable( // Permet de modifier la table relationnelle qui sera créée
        name = "wishlist", // nom de la table
        joinColumns = @JoinColumn(name = "user_id"), // renomme la colonne de l'objet courant dans la table relationnelle
        inverseJoinColumns = @JoinColumn(name = "game_id") // renomme la colonne de l'objet en relation dans la table relationnelle
    )
    @JsonView(JsonViews.UserShowView.class)
    private List<Game> wantedGames = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserShowView.class)
    private List<Review> reviews = new ArrayList<>();

    @JsonView(JsonViews.UserListView.class)
    public int getNbGames() {
        return this.userOwnGames.size();
    }

    @JsonView(JsonViews.UserShowView.class)
    public boolean isAdmin() {
        return roles.contains("[\"ROLE_ADMIN\"]");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}