package fr.poec.springboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.poec.springboot.json_view.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @JsonView(JsonViews.UserListView.class)
    private String email;

    @JsonView(JsonViews.UserMinimalView.class)
    private String name;

    @JsonView(JsonViews.UserListView.class)
    private String nickname;

    @JsonView(JsonViews.IgnoreView.class)
    private String password;

    @JsonView(JsonViews.UserShowView.class)
    private Date createdAt;

    @JsonView(JsonViews.UserMinimalView.class)
    private String profileImage;

    @JsonView(JsonViews.UserShowView.class)
    private String roles;

    @JsonView(JsonViews.UserShowView.class)
    private double wallet;

    @ManyToOne
    @JsonView(JsonViews.UserListView.class)
    private Country country;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserShowView.class)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserShowView.class)
    private List<UserOwnGame> games = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "wishlist",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "game_id")
            })
    @JsonView(JsonViews.UserShowView.class)
    private List<Game> wantedGames = new ArrayList<>();

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