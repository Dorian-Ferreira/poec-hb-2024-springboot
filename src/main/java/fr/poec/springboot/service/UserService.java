package fr.poec.springboot.service;

import fr.poec.springboot.DTO.UserPostDTO;
import fr.poec.springboot.DTO.UserPutDTO;
import fr.poec.springboot.custom_response.*;
import fr.poec.springboot.entity.User;
import fr.poec.springboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private CountryService countryService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomApiResponse findAll() {
        UserListCustomApiResponse apiResponse = new UserListCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());
        errorApiResponse.setEntity(User.class.getSimpleName());

        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            errorApiResponse.setCode(HttpStatus.NO_CONTENT.value());
            errorApiResponse.setMessage("There is no users.");

            return errorApiResponse;
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObjects(users);

        return apiResponse;
    }

    public CustomApiResponse show(Long id) {
        UserShowCustomApiResponse apiResponse = new UserShowCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());
        errorApiResponse.setEntity(User.class.getSimpleName());

        Optional<User> user = getById(id);

        if(user.isPresent()){
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setObject(user.get());

            return apiResponse;
        }

        errorApiResponse.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorApiResponse.setMessage("This user doesn't exist.");

        return errorApiResponse;
    }

    public CustomApiResponse persist(UserPutDTO userDTO, Long id) {
        UserShowCustomApiResponse apiResponse = new UserShowCustomApiResponse();
        ErrorCustomApiResponse errorApiResponse = new ErrorCustomApiResponse();

        apiResponse.setEntity(User.class.getSimpleName());
        errorApiResponse.setEntity(User.class.getSimpleName());

        User user;

        if(id == null) {
            if(userDTO.getPassword() == null) {
                errorApiResponse.setCode(HttpStatus.BAD_REQUEST.value());
                errorApiResponse.setMessage("Please input a password");
                return errorApiResponse;
            }
            user = userRepository.saveAndFlush(objectFromUserPostDto((UserPostDTO) userDTO));
        } else {
            user = objectFromUserPutDto(userDTO, id);
            if(user == null) {
                errorApiResponse.setCode(HttpStatus.BAD_REQUEST.value());
                errorApiResponse.setMessage("User doesn't exist");
                return errorApiResponse;
            }
            user = userRepository.saveAndFlush(user);
        }

        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setObject(user);

        return apiResponse;
    }

    private User objectFromUserPostDto(UserPostDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        user.setCreatedAt(new Date());
        user.setWallet(0);

        user.setNickname(userDTO.getNickname());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        user.setProfileImage(userDTO.getProfileImage());
        if(userDTO.getCountryId() != null) {
            countryService.getById(userDTO.getCountryId()).ifPresent(user::setCountry);
        }
        user.setRoles("[]");

        return user;
    }

    private User objectFromUserPutDto(UserPutDTO userDTO, Long id) {
        Optional<User> oUser = getById(id);
        if(oUser.isEmpty()) {
            return null;
        }
        User user = oUser.get();

        if(userDTO.getPassword() != null || !user.getPassword().isBlank()) {
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        }

        user.setNickname(userDTO.getNickname());
        user.setProfileImage(userDTO.getProfileImage());

        if(userDTO.getCountryId() != null) {
            countryService.getById(userDTO.getCountryId()).ifPresent(user::setCountry);
        } else {
            user.setCountry(null);
        }

        return user;
    }


    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    // Le paramètre est un "email", c'est juste que SpringBoot appelle l'identifiant de connexion "username"
    // On doit donc définir ici, comment SpringBoot va créer un objet "UserDetails" à partir de notre objet courant, ici User
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
