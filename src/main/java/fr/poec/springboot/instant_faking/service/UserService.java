package fr.poec.springboot.instant_faking.service;

import fr.poec.springboot.instant_faking.DTO.UserPostDTO;
import fr.poec.springboot.instant_faking.DTO.UserPutDTO;
import fr.poec.springboot.instant_faking.entity.User;
import fr.poec.springboot.instant_faking.exception.NotFoundInstantFakingException;
import fr.poec.springboot.instant_faking.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements DAOServiceInterface<User>,
                                    UserDetailsService {

    private UserRepository userRepository;

    private CountryService countryService;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        optionalUser.orElseThrow(() -> new NotFoundInstantFakingException("User", "email", email));
        return optionalUser.get();
    }

    public User getObjectById(Long id) {
        Optional<User> optionalUser = findById(id);
        optionalUser.orElseThrow(() -> new NotFoundInstantFakingException("User", "id", id));
        return optionalUser.get();
    }

    public User create(UserPostDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public User edit(Long id, UserPutDTO userPutDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.orElseThrow(() -> new NotFoundInstantFakingException("User", "id", id));
        User user = optionalUser.get();
        if (!userPutDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userPutDTO.getPassword()));
        }
        user.setProfileImage(userPutDTO.getProfileImage());
        user.setNickname(userPutDTO.getNickname());
        if (userPutDTO.getCountryId() != null) {
            user.setCountry(countryService.getObjectById(userPutDTO.getCountryId()));
        }
        userRepository.flush();
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = optionalUser.get();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                userGrantedAuthority(user.getRoles())
        );
    }

    private List<GrantedAuthority> userGrantedAuthority(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> roles = Collections.singletonList(role);
        roles.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (r.contains("ADMIN")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        });
        return authorities;
    }

}
